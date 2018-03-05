package taehwan;

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

import main.Main_GUI;

/*************************************************************************/
public class ExButton {
	// ��ü������ ���� ��������
	Frame mainFrame; // ������ ������ ���.
	private Label headerLabel; // ������ ��ܿ� ���� ��¿� ���Ǵ� ���̺�.
	private Label statusLabel; // ������ �ϴܿ� ���� ��¿� ���Ǵ� ���̺�.
	private Panel controlPanel; // ��ư�� ����� ���ؼ� ���
	static int score = 0; // ������ �����ϴ� score, ��ư�� ���ڸ� �ִ� A1�� A2, ���� �����ϴ� To ����.
	int A1;
	int A2;
	int To;
	int youof ;
	int youfald; // ������ Ʋ�ȴ��� üũ�ϱ� ���� ����
	Random Rand = new Random(); // ���������.
	int quiz, quiz2; // ������ �����ڿ� �ǿ����ڸ� ����ϱ� ���� ����.

	/*************************************************************************/
	public ExButton() {

		quiz = Rand.nextInt(5) + 1; // ������ ���� ����, ���ڸ� ���� �Դϴ�.
		quiz2 = Rand.nextInt(4) + 1; // ������ ���� ����, ���ڸ� ���� �Դϴ�.
		To = quiz + quiz2; // �������� ���ؼ� ���� ���մϴ�.
		prepareGUI(); // ���ο� �������� ����.

	}

	/*************************************************************************/

	public static  void main(String[] args) { // �����Դϴ�.

		// ���ο� ������ �ۼ�.
		ExButton Mathquiz = new ExButton(); // ��ư�� ����� ����
		Mathquiz.showButton(); // ��ư�� ���鷯 ���ϴ�.

	}

	/*************************************************************************/

	public void prepareGUI() { // Frame �� ���� ����
		// ������ ���� ����}
		mainFrame = new Frame("9 �� 0 ����"); // ���� ��������, ����ǥ���ٿ� ��������ϴ�.
		mainFrame.setSize(400, 400); // ������ ����,���� 400 �ȼ� ũ���Դϴ�.
		mainFrame.setLayout(new GridLayout(3, 1)); // �����ӿ� ���� ���빰�� ��µǴ� ������ �����ϴ�.���Ʒ��� 3ĭ.
		mainFrame.setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.
		mainFrame.setBackground(new Color(250, 150, 0)); // �������� ��� ����
		mainFrame.setLocationRelativeTo(null); // �����ϸ� ȭ���� ����� ������ �մϴ�.
		mainFrame.addWindowListener(new WindowAdapter() { // �ݱ� ��ư�� ������??
			public void windowClosing(WindowEvent windowEvent) {
				//System.exit(0); // ��ũ��Ʈ�� �����մϴ�.
				mainFrame.setVisible(false);
				mainFrame.dispose();
				Main_GUI.frame.setVisible(true);
			}
		}); // ������ ó������

		// ��ܿ� ������ ���� ���̺�
		headerLabel = new Label(); // ���ο� ���̺� ������
		headerLabel.setAlignment(Label.CENTER); // ����� ���̺��� ��������� �մϴ�.
		headerLabel.setText("   " + quiz + "  +  " + quiz2 + "  = ?  " + ""); // ������ ����մϴ�.
		headerLabel.setFont(new Font("", Font.BOLD, 60)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

		// �ϴܿ� ������ �����ִ� ���̺�
		statusLabel = new Label(); // ���ο� ���̺� ������
		statusLabel.setText("���� : " + score); // ������ ����մϴ�.
		statusLabel.setAlignment(Label.CENTER); // �ϴ��� ���̺��� ��������� �մϴ�.
		statusLabel.setFont(new Font("", Font.BOLD, 60)); // �۾��� ũ�⸦ �����մϴ�. ũ���� �۾��� ����ϴ�.

		controlPanel = new Panel(); // ��ư�� �����մϴ�.
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10)); // ��ư�� ���̾ƿ��� ����ϴ�.

		// ���ݱ��� �� ������ ���̺��� �����ӿ� ������ �߶��ָ�?!
		// �����ӿ� ���̺��� ���ɴϴ�. �ѵ�
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true); // �������� ���̰��մϴ�.
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
			} else if (W == 1) {
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
		btnfirst.setFont(new Font("�������", Font.BOLD, 50)); // ��ư�� ���� �۾� ���� ����.
		btnfirst.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ���ٴ�� Ŀ�� ����� �ٲߴϴ�.
		btnfirst.setBackground(new Color(255, 255, 255)); // ��ư�� ������ �Ͼ�� �Դϴ�.
		btnfirst.setPreferredSize(new Dimension(80, 80)); // ��ư�� ������
		btnfirst.setForeground(Color.BLACK); // ��ư�� ���� �۾��� ����.

		// ��ư ũ��, ���� ����
		Button btnSecond = new Button("" + A2); // ������ ���� ������ �κ����� �� �ƴϸ� ������ ���ϴ�.
		btnSecond.setFont(new Font("�������", Font.BOLD, 50)); // ��ư�� ���� �۾� ���� ����.
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
				int fun;
				fan = fan + 0; // �����ڵ�Ž�� ����.
				fan = Answer(A1); // ���ʹ�ư ���� �޼ҵ� ȣ��
				fun = youfald;
				if (fun == 1) {
					mainFrame.setVisible(false); // ������ ���
				}
			}
		});
		// ������ ��ư ������ �� ����
		btnSecond.addActionListener(new ActionListener() { // �� �༮�� ��ư�� �����ϰ� �ִ�.
			public void actionPerformed(ActionEvent e) { // ��ư�� ������??
				btnSecond.setBackground(Color.GREEN); // ������ ���� �ٲ�ϴ�.
				// ������ �³� ������
				int fan = 0; // ���� ���� �޾��� ���� �׷����� ������ 0 �ۿ� ����.
				int fun;
				fan = fan + 0; // �����ڵ�Ž�� ����.
				fan = Answer(A2); // �����ʹ�ư ���� �޼ҵ� ȣ��
				fun = youfald;
				if (fun == 1) {
					mainFrame.setVisible(false); // ������ ���
				}
			}
		});
		// ��ư�߰�
		controlPanel.add(btnfirst);
		controlPanel.add(btnSecond);
		mainFrame.setVisible(true); // ������ ���
		return o; // ������ó�� �� �������� ����.
	}

	/*****************************************************************/
	// �������� �κ�
	// ����Ȯ��
	public int Answer(int Answer) {
		if (To != 9) { // ������ �ٸ� ���ڰ� ���Դ�.
			if (Answer != To) { // Ʋ�� ���� �Է��ߴ��� Ȯ��..
				score += 18 + To; // ��� ������ Ʋ���� ����.
				System.out.println("0 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
			} else if (Answer == To) {
				youfald = 1; // ������ Ʋ������ ����.
				System.out.println("0 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				System.out.println("���� ������ " + score + " �� �Դϴ�.");

			}
		}

		if (To == 9) { // ������ 9�� ���Դ�.
			if (Answer == To) { // 9�� �Է��ߴ��� Ȯ��.
				score += 18 + To; // ��� ������ ������ ����.
				System.out.println("9 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
			} else if (Answer != To) {
				youfald = 1; // ������ Ʋ������ ����.
				System.out.println("9 ����\t" + " ���� : " + To + ",\t ���� : " + Answer + "\n"); // �����
				System.out.println("���� ������ " + score + " �� �Դϴ�.");

			}
		}
		statusLabel.setText("���� : " + score); // ������ ����
		main(null); // ������ ���� ���� �����Ƿ� �׳� ���ư��ϴ�.

		youof = youfald;
		// Ʋ������ Ȯ���ϰ� ���â�� ���
		if (youof == 1) {
			dispose();
			SUB d = new SUB();
			d.setSize(500, 500);
			d.setVisible(true);
			// sub�� ������ �ʰ� ����,,, �� ������ �ƿ�!
		}
		dispose(); // ������ �ݴ� �޼ҵ�
		return 0;// ���� ó�� �� ��ư ������� ����.
		// ���� ������ �ݱ�
	}

	/*************************************************************************/

	public void dispose() {
		mainFrame.dispose();

	}

}