package sanghee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class hangmanGUI extends JFrame {
	
	 String hiddenString; // ������ ���ڿ�(����)
	 StringBuffer outputString;// �÷��̾��� �Է¿� ���� ����� ������ ���ڿ�
	 StringBuffer inputString; // �÷��̾ �Է��� ���ڵ��� ����
	 int remaninder; // �� ���߰� �����ִ� ������ ��
	 int failed; // ������ Ƚ��
	
	hangmanGUI() {
		
		hiddenString = "hello";// ������ "hello"
		outputString = new StringBuffer();
		inputString = new StringBuffer();
		
		for (int i = 0; i < hiddenString.length(); i++) { // hiddenString�� ���� ����ŭ '-'���
			outputString.append('_');
		}
		
		remaninder = hiddenString.length(); // hiddenString�� ���ڼ��� ���㹮���� ���ڼ�
		failed = 0;
		
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
				
				JLabel lbl1 = new JLabel("�ܾ� (" + Hangman.outputString.length() + "����) :");
				lbl1.setFont(new Font(null, Font.BOLD, 25));
				lbl1.setBounds(130, 50, 170, 25);		
				this.add(lbl1);

				JLabel lbl2 = new JLabel(""+Hangman.outputString);
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
							checkChar(str.charAt(0)); // �� ���ڸ� �Է¹޾Ƽ� �������� Ȯ��
											

						
							
							
							
							//setVisible(false);
							/*String str = tf.getText();
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
							revalidate();
							repaint();*/
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
							JLabel lbl2 = new JLabel(""+Hangman.outputString);
							lbl2.setFont(new Font(null, Font.BOLD, 25));
							lbl2.setBounds(300, 50, 200, 25);		
							hangmanGUI.this.add(lbl2);
							
							System.out.println("@@@@@@@@ outputString : " + Hangman.outputString);
							System.out.println("@@@@@@@@ inputString : " + Hangman.inputString);
							System.out.println("@@@@@@@@ remaninder : " + Hangman.remaninder);
							System.out.println("@@@@@@@@ failed : " + Hangman.failed);
							tf.setText("");
							revalidate();
							repaint();
						}
					}
				});
				this.add(btn);
				

				setSize(600, 600);
				// Windows�� ȭ�鿡 ���̰� �Ѵ�.
				setVisible(true);	
	}

	public void checkChar(char guess) {
		boolean already = false;
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == guess) { // �̹��Է��ߴ� �������� ����
				System.out.println("\n �̹� �Էµ� �����Դϴ� �ٽ� �Է����ּ���,");
				already = true;
			}
		}

		if (!already) {
			// �Է��� ���ڵ��� ���ӿ� �߰�
			inputString.append(guess);

			boolean success = false;
			for (int i = 0; i < hiddenString.length(); i++) {
				if (hiddenString.charAt(i) == guess) { // ������ �ش繮�ڰ� �ִ��� ����
					System.out.println("�� ������ϴ�! ��");
					outputString.setCharAt(i, guess); // ������ ���ڰ� ������ -�� ���ڷ� ����
					remaninder--; // ���� ���ڼ� 1 ����
					success = true; // �Է��� ���ڰ� ������ �־����� ǥ��
				}
			}
			if (!success) {
				System.out.println("�� Ʋ�Ƚ��ϴ�! ��");
				failed++; // �Է��� ���ڰ� ������ ������ ����Ƚ���� 1����
			}
		}
	}
	
}