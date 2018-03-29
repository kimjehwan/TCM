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

	// ��ü������ ���� ��������
	Frame mainFrame; // ������ ������ ���.
	JProgressBar bar1;
	private Label headerLabel; // ������ ��ܿ� ���� ��¿� ���Ǵ� ���̺�.
	private Label statusLabel; // ������ �ϴܿ� ���� ��¿� ���Ǵ� ���̺�.
	private Panel controlPanel; // ��ư�� ����� ���ؼ� ���
	private Label Pausequiz; // �� ���� ���߾���?? �����ִ� ��
	static int score = 0; // ������ �����ϴ� score, ��ư�� ���ڸ� �ִ� A1�� A2, ���� �����ϴ� To ����.

	static int A1;

	static int A2;

	static int To;
	int youfald = 0; // ������ Ʋ�ȴ��� üũ�ϱ� ���� ����
	int stbar, stbar2; // ��������� Ȯ�ο�.
	Random Rand = new Random(); // Rand �̸����� ���������.
	static int quizAA; // �� ���� ���߾���??
	int quiz, quiz2; // ������ �����ڿ� �ǿ����ڸ� ����ϱ� ���� ����.
	/************* �ð������� �ɱ� ���� ���� **************/
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
		prepareGUI(); // ���ο� �������� ����.

		// *//**/*/*/*/*/*/*

	}

	/*************************************************************************/

	/*************************************************************************/

	public void prepareGUI() { // Frame �� ���� ����
		// ������ ���� ����
		quiz = Rand.nextInt(5) + 1; // ������ ���� ����, ���ڸ� ���� �Դϴ�.
		quiz2 = Rand.nextInt(4) + 1; // ������ ���� ����, ���ڸ� ���� �Դϴ�.
		To = quiz + quiz2; // �������� ���ؼ� ���� ���մϴ�.

		mainFrame = new Frame("0�� 9 ����"); // ���� ��������, ����ǥ���ٿ� ��������ϴ�.
		mainFrame.setSize(400, 500); // ������ ����,���� 400 �ȼ� ũ���Դϴ�.
		mainFrame.setLayout(new GridLayout(5, 1)); // �������� ĭ�� �����ϴ�.
		mainFrame.setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.
		mainFrame.setBackground(new Color(250, 150, 0)); // �������� ��� ����
		mainFrame.setLocationRelativeTo(null); // �����ϸ� ȭ���� ����� ������ �մϴ�.
		mainFrame.addWindowListener(new WindowAdapter() { // �ݱ� ��ư�� ������??
			public void windowClosing(WindowEvent windowEvent) {
				QuizPlay.quizAA = 0; // ���᰹�� �ʱ�ȭ
				QuizPlay.score = 0; // �������� �ʱ�ȭ
				mainFrame.dispose();
				th.stop();
				QuizMain.LLL.setVisible(true);
			}
		}); // ������ ó������

		// ��ܿ� ������ ���� ���̺�
		headerLabel = new Label(); // ���ο� ���̺� ������
		headerLabel.setAlignment(Label.CENTER); // ����� ���̺��� ��������� �մϴ�.
		headerLabel.setText("   " + quiz + "  +  " + quiz2 + "  = ?  " + ""); // ������ ����մϴ�.
		System.out.println(quiz + " " + quiz2);
		headerLabel.setFont(new Font("", Font.BOLD, 60)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

		/*
		 * // �ϴܿ� ���� ������� �����ִ� ���̺� statusLabel = new Label(); // ���ο� ���̺� ������
		 * statusLabel.setText("����� : " + score / 2); // ������ ����մϴ�.
		 * statusLabel.setAlignment(Label.CENTER); // �ϴ��� ���̺��� ��������� �մϴ�.
		 * statusLabel.setFont(new Font("", Font.BOLD, 30)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾���
		 * ����ϴ�.
		 */

		bar1 = new JProgressBar();
		bar1.setLayout(null);
		bar1.setStringPainted(true);
		bar1.setForeground(Color.BLACK);
		bar1.setFont(new Font("", Font.BOLD, 50));
		bar1.setString(score + "��");
		bar1.setValue(stbar);

		Pausequiz = new Label(); // ���ο� ���̺� ������
		Pausequiz.setText("���� : " + quizAA); // ������ ����մϴ�.
		Pausequiz.setAlignment(Label.CENTER); // �ϴ��� ���̺��� ��������� �մϴ�.
		Pausequiz.setFont(new Font("", Font.BOLD, 60)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

		controlPanel = new Panel(); // ��ư�� �����մϴ�.
		// ��ư�� �Ÿ��� �����մϴ�.
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10)); // ��ư�� ���̾ƿ��� ����ϴ�.

		// ���ݱ��� �� ������ ���̺��� �����ӿ� ������ �߶��ָ�?!
		// �����ӿ� ���̺��� ���ɴϴ�. �ѵ�
		// ������ �Ʒ��� ������� ���ɴϴ�.
		showButton();
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		// mainFrame.add(statusLabel);
		mainFrame.add(Pausequiz);
		mainFrame.add(bar1);
		mainFrame.setVisible(true); // �������� ���̰��մϴ�.
	}

	// �ð����� �Ŵ� �κ�
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
					System.out.println("����� : " + score + "/" + stbar2 + " ����" + stbar);
					// statusLabel.setText("����� : " + score / 2); // ������ ����
					Pausequiz.setText("���� : " + quizAA); // ������ ����
					stbar = 100 * score / stbar2;
					bar1.setString(score + "��");
					bar1.setValue(stbar);
					validate();

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ���ѽ����嵹����

		}

	}

	/*************************************************************************/
	// ��ư�� ���� ����
	public int showButton() {
		int o = 0; // ��ư ����� ���� ��ȯ�ޱ� ���� ����.
		// �������� ���� ���� ���
		int ran, W; // 2���� ����� ���� �ٸ��� ������ �ϱ� ���� ����.
		Random R = new Random();
		Random fan = new Random(); // ���ο� ���� ����.

		// �Ʒ��� ������ ���� ������ �������� �޶���.
		// �ʹ� ���...
		// To �� ������ ����.
		// �ϳ��� ������ ���� ���̰�
		// �ϳ��� �������� ���.
		// ������ 1 ~ 9 ���� ���ɴϴ�.
		// ���� �������� ������ ���� ����
		ran = fan.nextInt(7) + 1; // 8������ ������ �մϴ�.
		if (ran == To) {
			ran--; // �ߺ� �� ����
			// �׷����� 1 ���� ��Ű�°�...
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
		default: // ���� �����Ŵ� ����, �� �����̳� ��.
			; // �⺻���� �� ���� ����.
		}
		// ��ư ũ��, ���� ����
		Button btnfirst = new Button(A1 + ""); // ������ ���� ������ �κ����� �� �ƴϸ� ������ ���ϴ�.
		btnfirst.setFont(new Font("�������", Font.BOLD, 60)); // ��ư�� ���� �۾� ���� ����.
		btnfirst.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ���ٴ�� Ŀ�� ����� �ٲߴϴ�.
		btnfirst.setBackground(new Color(255, 255, 255)); // ��ư�� ������ �Ͼ�� �Դϴ�.
		btnfirst.setPreferredSize(new Dimension(80, 80)); // ��ư�� ������
		btnfirst.setForeground(Color.BLACK); // ��ư�� ���� �۾��� ����.

		// ��ư ũ��, ���� ����
		Button btnSecond = new Button("" + A2); // ������ ���� ������ �κ����� �� �ƴϸ� ������ ���ϴ�.
		btnSecond.setFont(new Font("�������", Font.BOLD, 60)); // ��ư�� ���� �۾� ���� ����.
		btnSecond.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ���ٴ�� Ŀ�� ����� �ٲߴϴ�.
		btnSecond.setBackground(new Color(255, 255, 255)); // ��ư�� ������ �Ͼ�� �Դϴ�.
		btnSecond.setPreferredSize(new Dimension(80, 80)); // ��ư�� ������
		btnSecond.setForeground(Color.black); // ��ư�� ���� �۾��� ����.

		// ���� ��ư ������ �� ����
		btnfirst.addActionListener(new ActionListener() { // �� �༮�� ��ư�� �����ϰ� �ִ�.
			public void actionPerformed(ActionEvent e) { // ��ư�� ������??
				btnfirst.setBackground(Color.GREEN); // ������ ���� �ٲ�ϴ�.
				// ������ �³� ������
				int fan = 0; // ���� ���� �޾��� ���� �׷����� ������ 0 �ۿ� ����.
				fan = Answer(A1); // ���ʹ�ư ���� �޼ҵ� ȣ��
				fan = fan + 0; // �����ڵ�Ž�� ����.

			}
		});
		// ������ ��ư ������ �� ����
		btnSecond.addActionListener(new ActionListener() { // �� �༮�� ��ư�� �����ϰ� �ִ�.
			public void actionPerformed(ActionEvent e) { // ��ư�� ������??
				btnSecond.setBackground(Color.GREEN); // ������ ���� �ٲ�ϴ�.
				// ������ �³� ������
				int fan = 0; // ���� ���� �޾��� ���� �׷����� ������ 0 �ۿ� ����.
				fan = Answer(A2); // �����ʹ�ư ���� �޼ҵ� ȣ��
				fan = fan + 0; // �����ڵ�Ž�� ����.
			}
		});
		// ��ư�߰�
		controlPanel.add(btnfirst);
		controlPanel.add(btnSecond);
		mainFrame.setVisible(true); // â ���̰� �ϱ�
		return o; // ������ó�� �� �������� ����.
	}

	/*****************************************************************/
	// �������� �κ�
	// ����Ȯ��
	public int Answer(int Answer) {
		if (To != 9) { // ������ �ٸ� ���ڰ� ���Դ�.
			if (Answer != To) { // Ʋ�� ���� �Է��ߴ��� Ȯ��..
				quizAA += 1;
				// score = 50; // 0 + To + quizAA; // ��� ������ Ʋ���� ����.
				System.out.println("0 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				mfdispose();
			} else if (Answer == To) {// ���ӿ��� �� ���������� ��µ��� �ʰ��մϴ�.
				youfald = 1; // ������ Ʋ������ ����.
				System.out.println("0 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				System.out.println("���� ������ " + score + " �� �Դϴ�.");
			}
		} else if (To == 9) { // ������ 9�� ���Դ�.
			if (Answer == To) { // 9�� �Է��ߴ��� Ȯ��.
				// score = 50; // + To + quizAA; // ��� ������ ������ ����.
				quizAA += 1;
				System.out.println("9 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				mfdispose();
			} else if (Answer != To) {// ���ӿ��� �� ���������� ��µ��� �ʰ��մϴ�.
				youfald = 1; // ������ Ʋ������ ����.
				System.out.println("9 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				System.out.println("���� ������ " + score + " �� �Դϴ�.");
			}
		}

		// Ʋ������ Ȯ���ϰ� ���â�� ���
		if (youfald == 1) {
			SUBM();
		}
		return 0;// ���� ó�� �� ��ư ������� ����.
		// ���� ������ �ݱ�
	}

	/*************************************************************************/

	public void mfdispose() { // ���������� �ݱ�
		showButton();
		mainFrame.dispose();
		prepareGUI();
	}

	public void SUBM() { // ���ӿ���â ����
		// ��ϰ���
		mainFrame.dispose();
		score = 0;
		QuizGameOver d = new QuizGameOver();
		d.setSize(500, 500);
		d.setVisible(true);
		QuizGameOver.RANA();
	}
}

/*********************************************************************/
