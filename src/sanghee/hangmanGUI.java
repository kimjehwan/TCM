package sanghee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jehwan.poker.PokerMain;

public class hangmanGUI extends JFrame {
	hangmanGUI(StringBuffer outputString) {
		char[] answer;
		
		// Window ������ ���� [X]��ư�� ������ ���α׷��� ����ǰ� �Ѵ�.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		setTitle("��� ����");
		// Windows�� ũ�⸦ �����Ѵ�. ����500 X ����500 �ȼ�ũ��� �����ߴ�.
		/////////////
		// ���̾ƿ��� FlowLayout���� ����
		this.setLayout(null);
		// �̹��������� 1���� �غ��Ѵ� �̴� 15��� 19�࿡�� ����Ѵ�.
		ImageIcon img1 = new ImageIcon("image/image/hangman_01.png");
		ImageIcon img2 = new ImageIcon("image/image/hangman_02.png");
		ImageIcon img3 = new ImageIcon("image/image/hangman_03.png");
		ImageIcon img4 = new ImageIcon("image/image/hangman_img04.png");
		ImageIcon img5 = new ImageIcon("image/image/hangman_img05.png");
		
		JLabel lbl1 = new JLabel("�ܾ� (" + outputString.length() + "����) :");
		lbl1.setFont(new Font(null, Font.BOLD, 25));
		lbl1.setBounds(130, 50, 170, 25);		
		this.add(lbl1);

		JLabel lbl2 = new JLabel(""+outputString);
		lbl2.setFont(new Font(null, Font.BOLD, 25));
		lbl2.setBounds(300, 50, 200, 25);		
		this.add(lbl2);
		
		// ���̺� ���� �Ǵ� �̹����� ǥ���ǵ��� �����Ѵ�.
		JLabel lbl3 = new JLabel(img1);
		lbl3.setBounds(210, 150, 163, 211);		
		this.add(lbl3);
		
		JLabel lbl4 = new JLabel("���ڸ� �Է��ϼ���(���� ��ȸ " + (6-Hangman.failed) +") : ");
		lbl4.setFont(new Font(null, Font.BOLD, 15));
		lbl4.setBounds(100, 450, 300, 25);		
		this.add(lbl4);
		
		JTextField tf = new JTextField();
		tf.setBounds(325, 450, 50, 25);	
		tf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				//���� Ű�� �������� key������ �����Ѵ�.
				int key = e.getKeyCode();
				///////////
				if(key==KeyEvent.VK_ENTER) {
					String str = tf.getText();
					Hangman.checkChar(str.charAt(0));
					
					hangmanGUI.this.remove(lbl3);
					JLabel lbl3 = new JLabel(img2);
					lbl3.setBounds(210, 150, 163, 211);		
					hangmanGUI.this.add(lbl3);
					
					remove(lbl4);
					JLabel lbl4 = new JLabel("���ڸ� �Է��ϼ���(���� ��ȸ " + (6-Hangman.failed) +") : ");
					lbl4.setFont(new Font(null, Font.BOLD, 15));
					lbl4.setBounds(100, 450, 300, 25);		
					hangmanGUI.this.add(lbl4);
					
					remove(lbl2);
					JLabel lbl2 = new JLabel(""+outputString);
					lbl2.setFont(new Font(null, Font.BOLD, 25));
					lbl2.setBounds(300, 50, 200, 25);		
					hangmanGUI.this.add(lbl2);
					
					System.out.println("@@@@@@@@ outputString : " + Hangman.outputString);
					System.out.println("@@@@@@@@ inputString : " + Hangman.inputString);
					System.out.println("@@@@@@@@ remaninder : " + Hangman.remaninder);
					System.out.println("@@@@@@@@ failed : " + Hangman.failed);
					tf.setText("");
					repaint();
					validate();
				}
				/////////// ����Ű�� ���Ͷ�� �ؽ�Ʈ �ʵ��� ������ �ؽ�Ʈ������ �߰�. �ٹٲ�. �ؽ�Ʈ�ʵ� ����.
			}
		});
		this.add(tf);

		JButton btn = new JButton("�Է�");
		btn.setBounds(400, 450, 60, 25);	
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn) {
/*					checkChar(readChar());
					hangmanGUI.this.remove(lbl3);
					JLabel lbl3 = new JLabel(img2);
					lbl3.setBounds(210, 150, 163, 211);		
					hangmanGUI.this.add(lbl3);
					repaint();
					revalidate();*/
				}
			}
		});
		this.add(btn);
		

		setSize(600, 600);
		// Windows�� ȭ�鿡 ���̰� �Ѵ�.
		setVisible(true);
	}

	
}