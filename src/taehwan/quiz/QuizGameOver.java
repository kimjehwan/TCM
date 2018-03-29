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
	// �츮�� ����ģ�� ��������.
	Button open; // ��ư�� ���� �����Դϴ�.
	Dimension dm; // ġ���ε� ���ο� ���� ���� �����ϴ� �뵵�Դϴ�. ������ ũ���.
	Label rank;
	Label gameover;

	// ���ӿ��� ȭ�� ���.
	public static void RANA() {
		if (QuizMain.sasc < QuizPlay.quizAA) {
			QuizMain.sasc = QuizPlay.quizAA;
			QuizPlay.quizAA = 0; // ���᰹�� �ʱ�ȭ
			QuizPlay.score = 0; // �������� �ʱ�ȭ
			System.out.println("��ũ ���� " + QuizMain.sasc + " ��");

		} else {
			//System.out.println("��ũ �� ����");
		}
	}

	public QuizGameOver() {
		super("::MainDemo:");
		// ������ �缳��
		setSize(500, 500); // ������ ũ�� ���� �۾��� ũ�� ���� �ϴ� �� ũ�� �մϴ�.
		setLocationRelativeTo(null); // �����ϸ� ȭ���� ����� ������ �մϴ�.
		setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.
		setBackground(new Color(250, 150, 0)); // �������� ��� ���� 
		// ��ǥ�� ��� ������Ʈ�� ���̰ų�,
		// ������Ʈ ����� �ְ� �ʹٸ� ���̾ƿ��� �����ϰ� ���.
		setLayout(null); // ���̾ƿ� ����

		dm = getSize(); // �ػ� ����� Ȯ��?!

		rank = new Label(); // ���ο� ���̺� ������
		rank.setBounds(150, 450, 230, 30);
		rank.setAlignment(Label.CENTER); // ����� ���̺��� ��������� �մϴ�.
		//rank.setText("�ִ� ���� " + Mainbar.sasc + " ��"); // 1�� ��ũ
		rank.setFont(new Font("", Font.BOLD, 25)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

		// ���ӿ��� ��ư ����.. ��ư �ȿ� ����� ���
		open = new Button("����� ���� ���� " + QuizPlay.quizAA + " �� �Դϴ�."); // ���ο��� ���� �����ͼ� ���.
		System.out.println("����� ���� ���� " + QuizPlay.quizAA + " �� �Դϴ�."); // �����
		open.setFont(new Font("�������", Font.BOLD, 30)); // ��ư�� �ִ� �۾��� ��Ʈ
		open.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ�� ���ٴ�� Ŀ�� ������ �����
		// ������ ���� ��ư ���� ����.
		if (QuizPlay.quizAA <= 20) { // 150 �� ���ϴ� �̰����� ó��.
			open.setBackground(new Color(255, 50, 0)); // �������� ������ ��� ����
		} // ��漳�� ��

		if (QuizPlay.quizAA > 20 && QuizPlay.quizAA <= 40) { // 151�� �̻� 500 �� ���ϴ� �̰����� ó��.
			open.setBackground(new Color(255, 255, 0)); // �߰����� ����� ��� ����
		} // ��漳�� ��

		if (QuizPlay.quizAA > 40) { // 501 �� �̻�� �̰����� ó��.
			open.setBackground(new Color(50, 255, 0)); // �������� �ʷϻ� ��� ����
		} // ��漳�� ��		
		gameover = new Label();
				gameover.setText("������ �������ϴ�.");
				gameover.setBounds(25,50,500,100);
				gameover.setFont(new Font("",Font.BOLD,50));
				add(gameover); // ���ӿ��� �޼���
		add(open); // ��ư �߰��ϱ�.
		// ������� ��ư ����
		open.setSize(500, 200); // ��ư ������ �����.
		Dimension bdm = open.getSize(); // ��ư�� ũ�⸦ ���մϴ�.

		// ��ư�� ����� �����Բ� �մϴ�.
		int centerX = dm.width / 2 - bdm.width / 2;
		int centerY = (dm.height - bdm.height) / 2;

		open.setLocation(centerX, centerY); // ��ư�� �����!!

		// ������ ����.. ���� implements������ this
		// ���ƴ� �̰Ž�?!
		open.addActionListener(this);

		// ���� Ʋ�ȴµ� ������ ������ �ʰ��մϴ�.
		// �ݱ� ��ư�� ������ ����.
		addWindowListener(new WindowAdapter() { // ��ư�� �����ϰ� �ִ�.
			public void windowClosing(WindowEvent e) { // ��ư�� ������?!
				QuizPlay.quizAA = 0; // ���᰹�� �ʱ�ȭ
				QuizPlay.score = 0; // �������� �ʱ�ȭ
				QuizMain.LLL.setVisible(true);
				dispose();
				// �ƹ��ϵ� �Ͼ�� �ʾҴٰ� �Ѵ�.
			}
		});
	}

	/*************************************************************************/

	// ���� ��� ��ư�� ������?!
	public void actionPerformed(ActionEvent e) { // ��ư ������?!
		// ����� ������ ��������.
		QuizPlay.quizAA = 0; // ���᰹�� �ʱ�ȭ
		QuizPlay.score = 0; // �������� �ʱ�ȭ
		QuizMain.LLL.setVisible(true);
		dispose();
	}

}
