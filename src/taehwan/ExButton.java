package taehwan;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import main.Main_GUI;

public class ExButton/* <JScrollPane> */ {
	// 전체적으로 사용하기위해 전역변수를 선언
	private Frame mainFrame;
	private Label headerLabel;
	private Label statusLabel;
	private Panel controlPanel;
	static int score = 0, A1, A2;
	// static int quiz, i, Ran;
	static Random Rand = new Random();
	static int quiz;

	public ExButton() {
		quiz = Rand.nextInt(9) + 1; // 디3:먼저 갱신되어 답을 입력시 다른 문제가 나감.
		prepareGUI();
	}

	public static void main(String[] args) {
		ExButton MathQuiz = new ExButton(); // 윈도우 프레임 생성
		MathQuiz.showButton(); // 버튼생성

	}

	public void prepareGUI() {
		// Frame 에 대한 셋팅
		// 윈도우 외형 셋팅
		mainFrame = new Frame("9 만 맞는 숫자");
		mainFrame.setSize(300, 300);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				mainFrame.setVisible(false);	//서브 프레임이므로 X클릭 시, 보이지 않게 하고
				mainFrame.dispose();// 자원을 회수
				new  Main_GUI();
			}
		});

		// 문제에 들어가는 난수 계산
		// quiz = Rand.nextInt(9) + 1;

		// 상단에 있는 라벨
		headerLabel = new Label();
		headerLabel.setAlignment(Label.CENTER);
		headerLabel.setText("" + quiz);

		// 하단 상태값 라벨
		statusLabel = new Label();
		statusLabel.setText("점수 : " + score);
		statusLabel.setAlignment(Label.CENTER);
		statusLabel.setSize(180, 50);

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	// 버튼의 외형 설정
	public int showButton() {
		int o = 0;
		// 선택지에 들어가는 난수 계산
		int /* A1 */ /* A2 */ ran;
		Random fan = new Random();
		// 선택지중복값제거.
		// 같은 선택지가 나오는 것을 방지
		ran = fan.nextInt(7) + 1;
		if (ran == quiz || ran == 9) {
			ran--; // 중복 값 제거
		}

		// 문제가 나올 때마다 선택지가 달라짐.
		switch (quiz) {
		case 1: {
			A1 = quiz;
			A2 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 2: {
			A2 = quiz;
			A1 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 3: {
			A2 = quiz;
			A1 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 4: {
			A1 = quiz;
			A2 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 5: {
			A1 = quiz;
			A2 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 6: {
			A2 = quiz;
			A1 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 7: {
			A2 = quiz;
			A1 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 8: {
			A1 = quiz;
			A2 = ran;
		}
			break;
		// A2 = FS.nextInt(8) + 1;
		case 9: {
			A1 = ran;
			A2 = 9;
		}
			break;
		default:
			;
		}
		// 버튼 크기, 색상 설정
		Button btnfirst = new Button("" + A1); // 선택지 내용 나오는 부분
		btnfirst.setBackground(new Color(255, 255, 255)); // 오렌지색 배경 설정
		btnfirst.setPreferredSize(new Dimension(100, 100));

		// 버튼 크기, 색상 설정
		Button btnSecond = new Button("" + A2); // 선택지 내용 나오는 부분
		btnSecond.setBackground(new Color(255, 255, 255)); // 오렌지색 배경 설정
		btnSecond.setPreferredSize(new Dimension(100, 100));

		// 왼쪽 버튼 눌렀을 때 판정
		btnfirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 누른게 맞나 판정함
				int fan = 0;
				fan = fan + 0;
				fan = Answer(A1); // 판정메소드
				// statusLabel.setText("점수 : " + score); // 점수출력

			}
		});
		// 오른쪽 버튼 눌렀을 때 판정
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 누른게 맞나 판정함
				int fan = 0;
				fan = fan + 0;
				fan = Answer(A2); // 판정메소드
				// statusLabel.setText("점수 : " + score); // 점수출력
			}
		});

		// 버튼추가
		controlPanel.add(btnfirst);
		controlPanel.add(btnSecond);

		// 창 보이게 하기
		mainFrame.setVisible(true);
		return o;
	}

	// 문제판정 부분
	// 문제확인
	public int Answer(int Answer) {
		if (quiz != 9) { // 문제가 다른 숫자가 나왔다.
			if (Answer != quiz) { // 틀린 값을 입력했는지 확인..
				score += 18 + quiz; // 답과 문제가 틀리면 가산.
				System.out.println("0 정답\t" + " 문제 : " + quiz + ",\t 선택 : " + Answer + "\n");
			} else if (Answer == quiz) {
				score -= 52 + quiz;
				System.out.println("0 오답\t" + " 문제 : " + quiz + ",\t 선택 : " + Answer + "\n");
			}
		}

		if (quiz == 9) { // 문제가 9가 나왔다.
			if (Answer == quiz) { // 9를 입력했는지 확인.
				score += 18 + quiz; // 답과 문제가 같으면 가산.
				System.out.println("9 정답\t" + " 문제 : " + quiz + ",\t 선택 : " + Answer + "\n");
			} else if (Answer != quiz) {
				score -= 52 + quiz;
				System.out.println("9 오답\t" + " 문제 : " + quiz + ",\t 선택 : " + Answer + "\n");
			}
		}

		statusLabel.setText("점수 : " + score);
		main(null);
		mainFrame.dispose();
		return 0;
	}
}