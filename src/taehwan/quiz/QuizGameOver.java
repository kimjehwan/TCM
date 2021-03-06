package taehwan.quiz;


import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuizGameOver extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	// 우리의 강한친구 전역변수.
	Button open; // 버튼을 위한 변수입니다.
	Dimension dm; // 치수인데 가로와 세로 값을 저장하는 용도입니다. 프레임 크기요.
	Label rank;
	Label gameover;

	// 게임오버 화면 출력.
	public static void RANA() {
		if (QuizMain.sasc < QuizPlay.quizAA) {
			QuizMain.sasc = QuizPlay.quizAA;
			QuizPlay.quizAA = 0; // 맞춘갯수 초기화
			QuizPlay.score = 0; // 남은생명 초기화
			System.out.println("랭크 갱신 " + QuizMain.sasc + " 점");

		} else {
			//System.out.println("랭크 미 갱신");
		}
	}

	public QuizGameOver() {
		super("::MainDemo:");
		// 프래임 재설정
		setSize(500, 500); // 프레임 크기 설정 글씨가 크게 들어가야 하니 좀 크게 합니다.
		setLocationRelativeTo(null); // 실행하면 화면의 가운데로 나오게 합니다.
		setResizable(false); // 창크기 조절 불가하게 합니다.
		setBackground(new Color(250, 150, 0)); // 오렌지색 배경 설정 
		// 좌표를 잡아 컴포넌트를 붙이거나,
		// 컴포넌트 사이즈를 주고 싶다면 레이아웃을 해제하고 사용.
		setLayout(null); // 레이아웃 해제

		dm = getSize(); // 해상도 사이즈를 확인?!

		rank = new Label(); // 새로운 레이블 생성자
		rank.setBounds(150, 450, 230, 30);
		rank.setAlignment(Label.CENTER); // 상단의 레이블이 가운데정렬을 합니다.
		//rank.setText("최다 정답 " + Mainbar.sasc + " 개"); // 1위 랭크
		rank.setFont(new Font("", Font.BOLD, 25)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

		// 게임오버 버튼 생성.. 버튼 안에 결과를 출력
		open = new Button("당신의 정답 수는 " + QuizPlay.quizAA + " 개 입니다."); // 메인에서 점수 가져와서 출력.
		System.out.println("당신의 정답 수는 " + QuizPlay.quizAA + " 개 입니다."); // 디버깅
		open.setFont(new Font("맑은고딕", Font.BOLD, 30)); // 버튼에 있는 글씨의 폰트
		open.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 갖다대면 커서 외형이 변경됨
		// 점수에 따라 버튼 색상 변경.
		if (QuizPlay.quizAA <= 20) { // 150 점 이하는 이것으로 처리.
			open.setBackground(new Color(255, 50, 0)); // 낮은점수 빨강색 배경 설정
		} // 배경설정 끝

		if (QuizPlay.quizAA > 20 && QuizPlay.quizAA <= 40) { // 151점 이상 500 점 이하는 이것으로 처리.
			open.setBackground(new Color(255, 255, 0)); // 중간점수 노란색 배경 설정
		} // 배경설정 끝

		if (QuizPlay.quizAA > 40) { // 501 점 이상는 이것으로 처리.
			open.setBackground(new Color(50, 255, 0)); // 높은점수 초록색 배경 설정
		} // 배경설정 끝		
		gameover = new Label();
				gameover.setText("게임이 끝났습니다.");
				gameover.setBounds(25,50,500,100);
				gameover.setFont(new Font("",Font.BOLD,50));
				add(gameover); // 게임오버 메세지
		add(open); // 버튼 추가하기.
		// 여기까지 버튼 외형
		open.setSize(500, 200); // 버튼 사이즈 만들기.
		Dimension bdm = open.getSize(); // 버튼의 크기를 정합니다.

		// 버튼이 가운데로 나오게끔 합니다.
		int centerX = dm.width / 2 - bdm.width / 2;
		int centerY = (dm.height - bdm.height) / 2;

		open.setLocation(centerX, centerY); // 버튼이 가운데로!!

		// 리스너 부착.. 위에 implements했으니 this
		// 으아니 이거슨?!
		open.addActionListener(this);

		// 문제 틀렸는데 문제가 나오지 않게합니다.
		// 닫기 버튼을 누르면 종료.
		addWindowListener(new WindowAdapter() { // 버튼을 감시하고 있다.
			public void windowClosing(WindowEvent e) { // 버튼을 눌렀다?!
				QuizPlay.quizAA = 0; // 맞춘갯수 초기화
				QuizPlay.score = 0; // 남은생명 초기화
				QuizMain.LLL.setVisible(true);
				dispose();
				// 아무일도 일어나지 않았다고 한다.
			}
		});
	}

	/*************************************************************************/

	// 점수 결과 버튼을 누르면?!
	public void actionPerformed(ActionEvent e) { // 버튼 눌렀나?!
		// 결과를 봤으니 나가야지.
		QuizPlay.quizAA = 0; // 맞춘갯수 초기화
		QuizPlay.score = 0; // 남은생명 초기화
		QuizMain.LLL.setVisible(true);
		dispose();
	}

}
