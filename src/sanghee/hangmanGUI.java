package sanghee;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class hangmanGUI extends JFrame {
	hangmanGUI() {
		// Window ������ ���� [X]��ư�� ������ ���α׷��� ����ǰ� �Ѵ�.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("��� ����");
		// Windows�� ũ�⸦ �����Ѵ�. ����500 X ����500 �ȼ�ũ��� �����ߴ�.
		/////////////
		// ���̾ƿ��� FlowLayout���� ����
		this.setLayout(null);
		// �̹��������� 1���� �غ��Ѵ� �̴� 15��� 19�࿡�� ����Ѵ�.
		ImageIcon img1 = new ImageIcon("image/hangman_img01.png");
		ImageIcon img2 = new ImageIcon("image/hangman_img02.png");
		ImageIcon img3 = new ImageIcon("image/hangman_img03.png");
		ImageIcon img4 = new ImageIcon("image/hangman_img04.png");
		ImageIcon img5 = new ImageIcon("image/hangman_img05.png");
		
		// ���̺� ���� �Ǵ� �̹����� ǥ���ǵ��� �����Ѵ�.
		JLabel lbl1 = new JLabel(img1);
		// ������ lbl1�� this(��ü ������)�� �����Ѵ�.
		this.add(lbl1);

		setSize(600, 600);
		// Windows�� ȭ�鿡 ���̰� �Ѵ�.
		setVisible(true);
	}

	
}