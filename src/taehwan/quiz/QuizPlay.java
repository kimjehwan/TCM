package taehwan.quiz;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import main.MainGUI;

/*************************************************************************/
public class QuizPlay extends JFrame implements Runnable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args) {
		QuizPlay b = new QuizPlay();
		b.showButton();
		b.prepareGUI();
		b.dispose();
	}

	// 전체적으로 사용될 전역변수
	Frame mainFrame; // 윈도우 생성에 사용.
	JProgressBar bar1;
	private Label headerLabel; // 윈도우 상단에 문제 출력에 사용되는 레이블.
	private Label statusLabel; // 윈도우 하단에 점수 출력에 사용되는 레이블.
	private Panel controlPanel; // 버튼을 만들기 위해서 사용
	private Label Pausequiz; // 몇 문제 맞추었지?? 보여주는 라벨
	static int score = 0; // 점수를 저장하는 score, 버튼에 숫자를 넣는 A1과 A2, 답을 저장하는 To 변수.

	static int A1;

	static int A2;

	static int To;
	int youfald = 0; // 문제가 틀렸는지 체크하기 위한 변수
	int stbar, stbar2; // 생명게이지 확인용.
	Random Rand = new Random(); // Rand 이름으로 난수만들기.
	static int quizAA; // 몇 문제 맞추었지??
	int quiz, quiz2; // 문제에 연산자와 피연산자를 출력하기 위한 변수.
	/************* 시간제한을 걸기 위한 변수 **************/
	long start_time, current_time;
	int count;
	long actual_time;
	boolean time_run = false;
	Thread th;
	String show_time;

	/*********************************************/

	/*************************************************************************/
	public QuizPlay() {
		if (score == 0) {
			score = 50;
			th = new Thread(this);
			th.start();
		}
		/*
		 * th = new Thread(this); th.start();
		 */
		prepareGUI(); // 새로운 프레임을 생성.

		// *//**/*/*/*/*/*/*

	}

	/*************************************************************************/

	/*************************************************************************/

	public void prepareGUI() { // Frame 에 대한 셋팅
		// 윈도우 외형 셋팅
		quiz = Rand.nextInt(5) + 1; // 연산자 난수 생성, 앞자리 숫자 입니다.
		quiz2 = Rand.nextInt(4) + 1; // 연산자 난수 생성, 뒷자리 숫자 입니다.
		To = quiz + quiz2; // 난수들을 더해서 답을 구합니다.

		mainFrame = new Frame("0과 9 게임"); // 게임 제목으로, 제목표시줄에 만들어집니다.
		mainFrame.setSize(400, 500); // 프레임 가로,세로 400 픽셀 크기입니다.
		mainFrame.setLayout(new GridLayout(5, 1)); // 프레임의 칸을 나눕니다.
		mainFrame.setResizable(false); // 창크기 조절 불가하게 합니다.
		mainFrame.setBackground(new Color(250, 150, 0)); // 오렌지색 배경 설정
		mainFrame.setLocationRelativeTo(null); // 실행하면 화면의 가운데로 나오게 합니다.
		mainFrame.addWindowListener(new WindowAdapter() { // 닫기 버튼을 누르면??
			public void windowClosing(WindowEvent windowEvent) {
				QuizPlay.quizAA = 0; // 맞춘갯수 초기화
				QuizPlay.score = 0; // 남은생명 초기화
				mainFrame.dispose();
				th.stop();
				QuizMain.LLL.setVisible(true);
			}
		}); // 리스너 처리구간

		// 상단에 문제를 내는 레이블
		headerLabel = new Label(); // 새로운 레이블 생성자
		headerLabel.setAlignment(Label.CENTER); // 상단의 레이블이 가운데정렬을 합니다.
		headerLabel.setText("   " + quiz + "  +  " + quiz2 + "  = ?  " + ""); // 문제를 출력합니다.
		System.out.println(quiz + " " + quiz2);
		headerLabel.setFont(new Font("", Font.BOLD, 60)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

		/*
		 * // 하단에 남은 생명력을 보여주는 레이블 statusLabel = new Label(); // 새로운 레이블 생성자
		 * statusLabel.setText("생명력 : " + score / 2); // 점수를 출력합니다.
		 * statusLabel.setAlignment(Label.CENTER); // 하단의 레이블이 가운데정렬을 합니다.
		 * statusLabel.setFont(new Font("", Font.BOLD, 30)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로
		 * 만듭니다.
		 */

		bar1 = new JProgressBar();
		bar1.setLayout(null);
		bar1.setStringPainted(true);
		bar1.setForeground(Color.BLACK);
		bar1.setFont(new Font("", Font.BOLD, 50));
		bar1.setString(score + "초");
		bar1.setValue(stbar);

		Pausequiz = new Label(); // 새로운 레이블 생성자
		Pausequiz.setText("정답 : " + quizAA); // 점수를 출력합니다.
		Pausequiz.setAlignment(Label.CENTER); // 하단의 레이블이 가운데정렬을 합니다.
		Pausequiz.setFont(new Font("", Font.BOLD, 60)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

		controlPanel = new Panel(); // 버튼을 생성합니다.
		// 버튼의 거리를 조절합니다.
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10)); // 버튼의 레이아웃을 만듭니다.

		// 지금까지 잘 조리된 레이블을 프레임에 찰지게 발라주면?!
		// 프레임에 레이블이 나옵니다. 뚜둥
		// 위부터 아래로 순서대로 나옵니다.
		showButton();
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		// mainFrame.add(statusLabel);
		mainFrame.add(Pausequiz);
		mainFrame.add(bar1);
		mainFrame.setVisible(true); // 프레임이 보이게합니다.
	}

	// 시간제한 거는 부분
	/*************************************************************************/

	public void run() {

		while (score > 0) {
			if (score > stbar2) {
				stbar2 = score;
				score = 50;

			}
			score -= 1; // + quizAA;
			try {
				if (score <= 0) {
					youfald = 1;
					if (youfald == 1) {
						SUBM();
					}
				} else if (youfald != 1) {
					Thread.sleep(1000);
					System.out.println("생명력 : " + score + "/" + stbar2 + " 남음" + stbar);
					// statusLabel.setText("생명력 : " + score / 2); // 점수를 갱신
					Pausequiz.setText("정답 : " + quizAA); // 점수를 갱신
					stbar = 100 * score / stbar2;
					bar1.setString(score + "초");
					bar1.setValue(stbar);
					validate();

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 무한스레드돌리기

		}

	}

	/*************************************************************************/
	// 버튼의 외형 설정
	public int showButton() {
		int o = 0; // 버튼 만들고 나서 반환받기 위한 변수.
		// 선택지에 들어가는 난수 계산
		int ran, W; // 2개의 답안이 서로 다르게 나오게 하기 위한 변수.
		Random R = new Random();
		Random fan = new Random(); // 새로운 난수 생성.

		// 아래는 문제가 나올 때마다 선택지가 달라짐.
		// 너무 길다...
		// To 는 출제된 문제.
		// 하나는 문제와 같은 값이고
		// 하나는 랜덤으로 출력.
		// 문제는 1 ~ 9 까지 나옵니다.
		// 같은 선택지가 나오는 것을 방지
		ran = fan.nextInt(7) + 1; // 8까지만 나오게 합니다.
		if (ran == To) {
			ran--; // 중복 값 제거
			// 그래봤자 1 감소 시키는거...
		}
		switch (To) {
		case 1: {
			A1 = To;
			A2 = ran;
		}
			break;
		case 2: {
			A2 = To;
			A1 = ran;
		}
			break;
		case 3: {
			A2 = To;
			A1 = ran;
		}
			break;
		case 4: {
			A1 = To;
			A2 = ran;
		}
			break;
		case 5: {
			A1 = To;
			A2 = ran;
		}
			break;
		case 6: {
			A2 = To;
			A1 = ran;
		}
			break;
		case 7: {
			A2 = To;
			A1 = ran;
		}
			break;
		case 8: {
			A1 = To;
			A2 = ran;
		}
			break;
		case 9: {
			W = R.nextInt(1);
			if (W == 0) {
				A1 = ran;
				A2 = 9;
			} else {
				A2 = ran;
				A1 = 9;
			}
		}
			break;
		default: // 예외 같은거는 없다, 넌 구경이나 해.
			; // 기본값은 할 일이 없다.
		}
		// 버튼 크기, 색상 설정
		Button btnfirst = new Button(A1 + ""); // 선택지 내용 나오는 부분으로 답 아니면 오답이 들어갑니다.
		btnfirst.setFont(new Font("맑은고딕", Font.BOLD, 60)); // 버튼에 들어가는 글씨 외형 설정.
		btnfirst.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서를 갖다대면 커서 모양을 바꿉니다.
		btnfirst.setBackground(new Color(255, 255, 255)); // 버튼의 색상은 하얀색 입니다.
		btnfirst.setPreferredSize(new Dimension(80, 80)); // 버튼의 사이즈
		btnfirst.setForeground(Color.BLACK); // 버튼에 들어가는 글씨의 색상.

		// 버튼 크기, 색상 설정
		Button btnSecond = new Button("" + A2); // 선택지 내용 나오는 부분으로 답 아니면 오답이 들어갑니다.
		btnSecond.setFont(new Font("맑은고딕", Font.BOLD, 60)); // 버튼에 들어가는 글씨 외형 설정.
		btnSecond.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서를 갖다대면 커서 모양을 바꿉니다.
		btnSecond.setBackground(new Color(255, 255, 255)); // 버튼의 색상은 하얀색 입니다.
		btnSecond.setPreferredSize(new Dimension(80, 80)); // 버튼의 사이즈
		btnSecond.setForeground(Color.black); // 버튼에 들어가는 글씨의 색상.

		// 왼쪽 버튼 눌렀을 때 판정
		btnfirst.addActionListener(new ActionListener() { // 이 녀석은 버튼을 감시하고 있다.
			public void actionPerformed(ActionEvent e) { // 버튼을 눌렀나??
				btnfirst.setBackground(Color.GREEN); // 누르면 색이 바뀝니다.
				// 누른게 맞나 판정함
				int fan = 0; // 판정 값을 받아줄 변수 그래봤자 받을건 0 밖에 없다.
				fan = Answer(A1); // 왼쪽버튼 판정 메소드 호출
				fan = fan + 0; // 데드코드탐지 제거.

			}
		});
		// 오른쪽 버튼 눌렀을 때 판정
		btnSecond.addActionListener(new ActionListener() { // 이 녀석은 버튼을 감시하고 있다.
			public void actionPerformed(ActionEvent e) { // 버튼을 눌렀나??
				btnSecond.setBackground(Color.GREEN); // 누르면 색이 바뀝니다.
				// 누른게 맞나 판정함
				int fan = 0; // 판정 값을 받아줄 변수 그래봤자 받을건 0 밖에 없다.
				fan = Answer(A2); // 오른쪽버튼 판정 메소드 호출
				fan = fan + 0; // 데드코드탐지 제거.
			}
		});
		// 버튼추가
		controlPanel.add(btnfirst);
		controlPanel.add(btnSecond);
		mainFrame.setVisible(true); // 창 보이게 하기
		return o; // 프레임처리 후 메인으로 복귀.
	}

	/*****************************************************************/
	// 문제판정 부분
	// 문제확인
	public int Answer(int Answer) {
		if (To != 9) { // 문제가 다른 숫자가 나왔다.
			if (Answer != To) { // 틀린 값을 입력했는지 확인..
				quizAA += 1;
				// score = 50; // 0 + To + quizAA; // 답과 문제가 틀리면 가산.
				System.out.println("0 정답\t" + " 문제 : " + To + ",\t 선택 : " + Answer + "\n"); // 디버깅
				mfdispose();
			} else if (Answer == To) {// 게임오버 시 다음문제가 출력되지 않게합니다.
				youfald = 1; // 문제가 틀렸음을 적용.
				System.out.println("0 오답\t" + " 문제 : " + To + ",\t 선택 : " + Answer + "\n"); // 디버깅
				System.out.println("최종 점수는 " + score + " 점 입니다.");
			}
		} else if (To == 9) { // 문제가 9가 나왔다.
			if (Answer == To) { // 9를 입력했는지 확인.
				// score = 50; // + To + quizAA; // 답과 문제가 같으면 가산.
				quizAA += 1;
				System.out.println("9 정답\t" + " 문제 : " + To + ",\t 선택 : " + Answer + "\n"); // 디버깅
				mfdispose();
			} else if (Answer != To) {// 게임오버 시 다음문제가 출력되지 않게합니다.
				youfald = 1; // 문제가 틀렸음을 적용.
				System.out.println("9 오답\t" + " 문제 : " + To + ",\t 선택 : " + Answer + "\n"); // 디버깅
				System.out.println("최종 점수는 " + score + " 점 입니다.");
			}
		}

		// 틀린것을 확인하고 결과창을 출력
		if (youfald == 1) {
			SUBM();
		}
		return 0;// 정답 처리 후 버튼 기능으로 복귀.
		// 이전 프레임 닫기
	}

	/*************************************************************************/

	public void mfdispose() { // 메인프레임 닫기
		showButton();
		mainFrame.dispose();
		prepareGUI();
	}

	public void SUBM() { // 게임오버창 열기
		// 기록갱신
		mainFrame.dispose();
		score = 0;
		QuizGameOver d = new QuizGameOver();
		d.setSize(500, 500);
		d.setVisible(true);
		QuizGameOver.RANA();
	}
}

/*********************************************************************/
