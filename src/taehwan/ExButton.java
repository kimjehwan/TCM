package taehwan;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import main.Main_GUI;

public class ExButton/* <JScrollPane> */ {
	// ��ü������ ����ϱ����� ���������� ����
	private Frame mainFrame;
	private Label headerLabel;
	private Label statusLabel;
	private Panel controlPanel;
	static int score = 0, A1, A2;
	// static int quiz, i, Ran;
	static Random Rand = new Random();
	static int quiz;

	public ExButton() {
		quiz = Rand.nextInt(9) + 1; // ��3:���� ���ŵǾ� ���� �Է½� �ٸ� ������ ����.
		prepareGUI();
	}

	public static void main(String[] args) {
		ExButton MathQuiz = new ExButton(); // ������ ������ ����
		MathQuiz.showButton(); // ��ư����

	}

	public void prepareGUI() {
		// Frame �� ���� ����
		// ������ ���� ����
		mainFrame = new Frame("9 �� �´� ����");
		mainFrame.setSize(300, 300);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				mainFrame.setVisible(false);	//���� �������̹Ƿ� XŬ�� ��, ������ �ʰ� �ϰ�
				mainFrame.dispose();// �ڿ��� ȸ��
				new  Main_GUI();
			}
		});

		// ������ ���� ���� ���
		// quiz = Rand.nextInt(9) + 1;

		// ��ܿ� �ִ� ��
		headerLabel = new Label();
		headerLabel.setAlignment(Label.CENTER);
		headerLabel.setText("" + quiz);

		// �ϴ� ���°� ��
		statusLabel = new Label();
		statusLabel.setText("���� : " + score);
		statusLabel.setAlignment(Label.CENTER);
		statusLabel.setSize(180, 50);

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	// ��ư�� ���� ����
	public int showButton() {
		int o = 0;
		// �������� ���� ���� ���
		int /* A1 */ /* A2 */ ran;
		Random fan = new Random();
		// �������ߺ�������.
		// ���� �������� ������ ���� ����
		ran = fan.nextInt(7) + 1;
		if (ran == quiz || ran == 9) {
			ran--; // �ߺ� �� ����
		}

		// ������ ���� ������ �������� �޶���.
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
		// ��ư ũ��, ���� ����
		Button btnfirst = new Button("" + A1); // ������ ���� ������ �κ�
		btnfirst.setBackground(new Color(255, 255, 255)); // �������� ��� ����
		btnfirst.setPreferredSize(new Dimension(100, 100));

		// ��ư ũ��, ���� ����
		Button btnSecond = new Button("" + A2); // ������ ���� ������ �κ�
		btnSecond.setBackground(new Color(255, 255, 255)); // �������� ��� ����
		btnSecond.setPreferredSize(new Dimension(100, 100));

		// ���� ��ư ������ �� ����
		btnfirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ �³� ������
				int fan = 0;
				fan = fan + 0;
				fan = Answer(A1); // �����޼ҵ�
				// statusLabel.setText("���� : " + score); // �������

			}
		});
		// ������ ��ư ������ �� ����
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ �³� ������
				int fan = 0;
				fan = fan + 0;
				fan = Answer(A2); // �����޼ҵ�
				// statusLabel.setText("���� : " + score); // �������
			}
		});

		// ��ư�߰�
		controlPanel.add(btnfirst);
		controlPanel.add(btnSecond);

		// â ���̰� �ϱ�
		mainFrame.setVisible(true);
		return o;
	}

	// �������� �κ�
	// ����Ȯ��
	public int Answer(int Answer) {
		if (quiz != 9) { // ������ �ٸ� ���ڰ� ���Դ�.
			if (Answer != quiz) { // Ʋ�� ���� �Է��ߴ��� Ȯ��..
				score += 18 + quiz; // ��� ������ Ʋ���� ����.
				System.out.println("0 ����\t" + " ���� : " + quiz + ",\t ���� : " + Answer + "\n");
			} else if (Answer == quiz) {
				score -= 52 + quiz;
				System.out.println("0 ����\t" + " ���� : " + quiz + ",\t ���� : " + Answer + "\n");
			}
		}

		if (quiz == 9) { // ������ 9�� ���Դ�.
			if (Answer == quiz) { // 9�� �Է��ߴ��� Ȯ��.
				score += 18 + quiz; // ��� ������ ������ ����.
				System.out.println("9 ����\t" + " ���� : " + quiz + ",\t ���� : " + Answer + "\n");
			} else if (Answer != quiz) {
				score -= 52 + quiz;
				System.out.println("9 ����\t" + " ���� : " + quiz + ",\t ���� : " + Answer + "\n");
			}
		}

		statusLabel.setText("���� : " + score);
		main(null);
		mainFrame.dispose();
		return 0;
	}
}