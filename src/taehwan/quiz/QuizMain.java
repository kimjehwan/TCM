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
	static int sasc = 10; // ���� �����.

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
			Label descriptionLabel; // ������ �ϴܿ� ���� ��¿� ���Ǵ� ���̺�.
			Label descriptionLabel2;
			Label scropLabel;
			JButton start = new JButton("���ӽ���");
			JButton end = new JButton("��������");
			LLL = new Frame("0�� 9 ����"); // ���� ��������, ����ǥ���ٿ� ��������ϴ�.
			LLL.setSize(400, 500); // ������ ����,���� 400 �ȼ� ũ���Դϴ�.
			LLL.setLayout(null); // �����ӿ� ���� ���빰�� ��µǴ� ������ �����ϴ�.���Ʒ��� 3ĭ.
			LLL.setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.
			LLL.setBackground(new Color(250, 170, 0)); // �������� ��� ����
			LLL.setLocationRelativeTo(null); // �����ϸ� ȭ���� ����� ������ �մϴ�.Label headerLabel;
			LLL.setTitle("0�� 9 ����");

			setLayout(null);
			setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.

			scropLabel = new Label(); // ���ο� ���̺� ������
			scropLabel.setBounds(150, 450, 230, 30);
			scropLabel.setAlignment(Label.CENTER); // ����� ���̺��� ��������� �մϴ�.
			scropLabel.setText("���� ���� �� " + sasc + " ��"); // 1�� ��ũ
			scropLabel.setFont(new Font("", Font.BOLD, 25)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

			headerLabel = new Label(); // ���ο� ���̺� ������
			headerLabel.setBounds(0, 0, 400, 200);
			headerLabel.setAlignment(Label.CENTER); // ����� ���̺��� ��������� �մϴ�.
			headerLabel.setText("0�� 9 ����"); // �����̸� ���
			headerLabel.setFont(new Font("", Font.BOLD, 50)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

			descriptionLabel = new Label(); // ���ο� ���̺� ������
			descriptionLabel.setBounds(0, 370, 400, 25);
			descriptionLabel.setAlignment(Label.CENTER); // �ϴ� ���� ���̺��� ��������� �մϴ�.
			descriptionLabel.setText("9�� ���߸� �Ǵ� �����Դϴ�.");// ����
			descriptionLabel.setFont(new Font("", Font.BOLD, 15)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

			descriptionLabel2 = new Label(); //
			descriptionLabel2.setBounds(0, 400, 400, 25);
			descriptionLabel2.setAlignment(Label.CENTER); // �ϴ� ���� ���̺��� ��������� �մϴ�.
			descriptionLabel2.setText("������ ���߸� ������� �����پ��ϴ�");// ����
			descriptionLabel2.setFont(new Font("", Font.BOLD, 15)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

			LLL.getSize();
			LLL.setSize(400, 500);
			System.out.println("" + getSize());
			start.setBounds(100, 170, 200, 80);
			start.setFont(new Font("", Font.BOLD, 30)); // ��ư�� ���� �۾� ���� ����.
			start.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ���ٴ�� Ŀ�� ����� �ٲߴϴ�.
			// start.setBackground(new Color(255, 255, 255)); // ��ư�� ������ �Ͼ�� �Դϴ�.

			end.setBounds(100, 270, 200, 80);
			end.setFont(new Font("", Font.BOLD, 30)); // ��ư�� ���� �۾� ���� ����.
			end.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ���ٴ�� Ŀ�� ����� �ٲߴϴ�.
			// end.setBackground(new Color(255, 255, 255)); // ��ư�� ������ �Ͼ�� �Դϴ�.

			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LLL.setVisible(false);
					QuizPlay b = new QuizPlay(); // ��ư�� ����� ����
					b.showButton();
					b.th.start();
					b.prepareGUI();
					/* ������ ���� �ʱ�ȭ */
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
					/* ��ũ���� */
					QuizGameOver.RANA();
					scropLabel.setText("���� ���� �� " + sasc + " ��"); // 1�� ��ũ
					/********/
				}

				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			LLL.addWindowListener(new WindowAdapter() { // ��ư�� �����ϰ� �ִ�.
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
