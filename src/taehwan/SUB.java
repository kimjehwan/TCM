package taehwan;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.Main_GUI;
import main.Player;

public class SUB extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �츮�� ����ģ�� ��������.
	Button open; // ��ư�� ���� �����Դϴ�.
	Dimension dm; // ġ���ε� ���ο� ���� ���� �����ϴ� �뵵�Դϴ�. ������ ũ���.

	/*************************************************************************/

	// ���ӿ��� ȭ�� ���.
	public SUB() {
		super("::MainDemo:");
		dispose();
		// ������ �缳��
		setSize(500, 500); // ������ ũ�� ���� �۾��� ũ�� ���� �ϴ� �� ũ�� �մϴ�.
		setLocationRelativeTo(null); // �����ϸ� ȭ���� ����� ������ �մϴ�.
		setResizable(false); // âũ�� ���� �Ұ��ϰ� �մϴ�.
		setBackground(new Color(250, 150, 0)); // �������� ��� ����
		// ��ǥ�� ��� ������Ʈ�� ���̰ų�,
		// ������Ʈ ����� �ְ� �ʹٸ� ���̾ƿ��� �����ϰ� ���.
		setLayout(null); // ���̾ƿ� ����

		dm = getSize(); // �ػ� ����� Ȯ��?!

		// ���ӿ��� ��ư ����.. ��ư �ȿ� ����� ���
		open = new Button(Player.getId() + " ���� ������ " + ExButton.score + " �� �Դϴ�."); // ���ο��� ���� �����ͼ� ���.
		System.out.println("����� ������ " + ExButton.score + " �� �Դϴ�."); // �����
		open.setFont(new Font("�������", Font.BOLD, 30)); // ��ư�� �ִ� �۾��� ��Ʈ
		open.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ�� ���ٴ�� Ŀ�� ������ �����
		// ������ ���� ��ư ���� ����.
		if (ExButton.score <= 150) { // 150 �� ���ϴ� �̰����� ó��.
			open.setBackground(new Color(255, 50, 0)); // �������� ������ ��� ����
		} // ��漳�� ��

		if (ExButton.score > 150 && ExButton.score <= 500) { // 151�� �̻� 500 �� ���ϴ� �̰����� ó��.
			open.setBackground(new Color(255, 255, 0)); // �߰����� ����� ��� ����
		} // ��漳�� ��

		if (ExButton.score > 500) { // 501 �� �̻�� �̰����� ó��.
			open.setBackground(new Color(50, 255, 0)); // �������� �ʷϻ� ��� ����
		} // ��漳�� ��
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
		addWindowListener(new WindowAdapter() { // �ݱ� ��ư�� �����ϰ� �ִ�.
			public void windowClosing(WindowEvent e) { // ��ư�� ������?!
				// System.exit(0);
				// �ƹ��ϵ� �Ͼ�� �ʾҴٰ� �Ѵ�.
				 dispose();
					Main_GUI.frame.setVisible(true);
				 //new Main_GUI();
			}
		});
	}

	/*************************************************************************/

	// ���� ��� ��ư�� ������?!
	public void actionPerformed(ActionEvent e) { // ��ư ������?!
		// ����� ������ ��������.
		dispose();
		Main_GUI.frame.setVisible(true);
		//new Main_GUI();
		// System.exit(0);

	}

}
