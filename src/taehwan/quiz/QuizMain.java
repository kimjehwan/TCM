package taehwan.quiz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.MainGUI;

public class QuizMain {
	static Frame LLL;
	static int sasc = 10; // 점수 저장용.

	public static void main(String[] args) {
		new Mainmenu();
	}

	static class Mainmenu extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Mainmenu() {
			Label headerLabel;
			Label descriptionLabel; // 윈도우 하단에 점수 출력에 사용되는 레이블.
			Label descriptionLabel2;
			Label scropLabel;
			JButton start = new JButton("게임시작");
			JButton end = new JButton("게임종료");
			LLL = new Frame("0과 9 게임"); // 게임 제목으로, 제목표시줄에 만들어집니다.
			LLL.setSize(400, 500); // 프레임 가로,세로 400 픽셀 크기입니다.
			LLL.setLayout(null); // 프레임에 들어가는 내용물이 출력되는 공간을 나눕니다.위아래로 3칸.
			LLL.setResizable(false); // 창크기 조절 불가하게 합니다.
			LLL.setBackground(new Color(250, 170, 0)); // 오렌지색 배경 설정
			LLL.setLocationRelativeTo(null); // 실행하면 화면의 가운데로 나오게 합니다.Label headerLabel;
			LLL.setTitle("0과 9 게임");

			setLayout(null);
			setResizable(false); // 창크기 조절 불가하게 합니다.

			scropLabel = new Label(); // 새로운 레이블 생성자
			scropLabel.setBounds(150, 450, 230, 30);
			scropLabel.setAlignment(Label.CENTER); // 상단의 레이블이 가운데정렬을 합니다.
			scropLabel.setText("많이 맞춘 수 " + sasc + " 개"); // 1위 랭크
			scropLabel.setFont(new Font("", Font.BOLD, 25)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

			headerLabel = new Label(); // 새로운 레이블 생성자
			headerLabel.setBounds(0, 0, 400, 200);
			headerLabel.setAlignment(Label.CENTER); // 상단의 레이블이 가운데정렬을 합니다.
			headerLabel.setText("0과 9 게임"); // 게임이름 출력
			headerLabel.setFont(new Font("", Font.BOLD, 50)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

			descriptionLabel = new Label(); // 새로운 레이블 생성자
			descriptionLabel.setBounds(0, 370, 400, 25);
			descriptionLabel.setAlignment(Label.CENTER); // 하단 설명 레이블이 가운데정렬을 합니다.
			descriptionLabel.setText("9만 맞추면 되는 게임입니다.");// 설명
			descriptionLabel.setFont(new Font("", Font.BOLD, 15)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

			descriptionLabel2 = new Label(); //
			descriptionLabel2.setBounds(0, 400, 400, 25);
			descriptionLabel2.setAlignment(Label.CENTER); // 하단 설명 레이블이 가운데정렬을 합니다.
			descriptionLabel2.setText("문제를 맞추면 생명력이 빨리줄어듭니다");// 설명
			descriptionLabel2.setFont(new Font("", Font.BOLD, 15)); // 글씨의 크기를 설정합니다. 크고굵은 글씨로 만듭니다.

			LLL.getSize();
			LLL.setSize(400, 500);
			System.out.println("" + getSize());
			start.setBounds(100, 170, 200, 80);
			start.setFont(new Font("", Font.BOLD, 30)); // 버튼에 들어가는 글씨 외형 설정.
			start.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서를 갖다대면 커서 모양을 바꿉니다.
			// start.setBackground(new Color(255, 255, 255)); // 버튼의 색상은 하얀색 입니다.

			end.setBounds(100, 270, 200, 80);
			end.setFont(new Font("", Font.BOLD, 30)); // 버튼에 들어가는 글씨 외형 설정.
			end.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서를 갖다대면 커서 모양을 바꿉니다.
			// end.setBackground(new Color(255, 255, 255)); // 버튼의 색상은 하얀색 입니다.

			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LLL.setVisible(false);
					QuizPlay b = new QuizPlay(); // 버튼의 기능을 생성
					b.showButton();
					b.th.start();
					b.prepareGUI();
					/* 게임의 점수 초기화 */
					/**************/
				}
			});
			end.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//new Main_GUI();
					LLL.dispose();
					MainGUI.frame.setVisible(true);
				}
			});
			LLL.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent arg0) {

				}

				public void mousePressed(MouseEvent arg0) {

				}

				public void mouseExited(MouseEvent arg0) {

				}

				public void mouseEntered(MouseEvent arg0) {
					/* 랭크갱신 */
					QuizGameOver.RANA();
					scropLabel.setText("많이 맞춘 수 " + sasc + " 개"); // 1위 랭크
					/********/
				}

				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			LLL.addWindowListener(new WindowAdapter() { // 버튼을 감시하고 있다.
				public void windowClosing(WindowEvent e) {
					//new Main_GUI();
					LLL.dispose();
					MainGUI.frame.setVisible(true);
				}
			});

			LLL.add(end);
			LLL.add(start);
			LLL.add(headerLabel);
			LLL.add(descriptionLabel);
			LLL.add(scropLabel);
			LLL.add(descriptionLabel2);
			LLL.setVisible(true);
			scropLabel.validate();
		}

	}
}
