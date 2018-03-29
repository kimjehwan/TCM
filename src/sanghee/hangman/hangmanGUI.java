package sanghee.hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.MainGUI;

public class hangmanGUI extends JFrame {
	
	JLabel lbl2;	//������� ���� ���� �޶����� outputString�� ǥ���� ���̺� ����
	JLabel lbl3;	//��� �̹����� ���� ���̺� ����
	JLabel lbl4;	//���ڸ� �Է��� �� �ִ� �ȳ� ���ڿ��� ���� ���̺� ����
	ImageIcon[] img = new ImageIcon[8];	//�̹��������ܵ��� ���� �� �ִ� �迭 ����
	int result;	 //����� ����, �̹� �Է��� ���������� Ȯ���� �� �ִ� ���� ���� ����
	
	public hangmanGUI() {
	
		JFrame frame =this;
		frame.addWindowListener(new WindowAdapter() {
			// Window ������ ���� [X]��ư�� ������
			public void windowClosing(WindowEvent windowEvent) {
				frame.setVisible(false);
				frame.dispose();
				MainGUI.frame.setVisible(true);
				//���� ��ǰ��� �ڿ��� ȸ���ϰ� ������������ �����ش�.
			}
		});
				this.getContentPane().setBackground(Color.white);
				setTitle("��� ����");
				this.setLayout(null);
				
				//�̹������� �̹��� �迭 ������ ��� �ش�.
				img[0] = 	new ImageIcon("image/image/hangman_0.png");
				img[1] = new ImageIcon("image/image/hangman_1.png");
				img[2] = new ImageIcon("image/image/hangman_2.png");
				img[3] = new ImageIcon("image/image/hangman_3.png");
				img[4] = new ImageIcon("image/image/hangman_4.png");
				img[5] = new ImageIcon("image/image/hangman_5.png");
				img[6] = new ImageIcon("image/image/hangman_6.png");
				img[7] = new ImageIcon("image/image/hangman_7.png");
				
				//���� ���ڼ��� �˷��ִ� ���ڿ��� ���� ���̺� ����
				JLabel lbl1 = new JLabel("�ܾ� (" + Hangman.outputString.length() + "����) :");
				lbl1.setFont(new Font(null, Font.BOLD, 25));
				lbl1.setBounds(130, 50, 170, 25);		
				this.add(lbl1);
				
				
				lbl2 = new JLabel(""+Hangman.outputString);
				lbl2.setFont(new Font(null, Font.BOLD, 25));
				lbl2.setBounds(300, 50, 200, 25);		
				this.add(lbl2);
				
				
				lbl3 = new JLabel(img[0]);
				lbl3.setBounds(210, 150, 163, 211);		
				this.add(lbl3);
				

				lbl4 = new JLabel("���ڸ� �Է��ϼ���(���� ��ȸ " + (6-Hangman.failed) +") : ");
				lbl4.setFont(new Font(null, Font.BOLD, 15));
				lbl4.setBounds(100, 450, 300, 25);		
				this.add(lbl4);
							
				//������ �Է��� �� �ִ� �Է�â�� ��Ÿ���� ����
				JTextField tf = new JTextField();
				tf.setBounds(325, 450, 50, 25);	
				this.add(tf);
				
				//�Է¹�ư�� ��Ÿ���� ����
				JButton btn = new JButton("�Է�");
				btn.setBounds(400, 450, 60, 25);	
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object obj = e.getSource();
						if((JButton)obj==btn) {
							String str = tf.getText();
							if(str.equals("")) {//���� �Է¹��ڰ� ���ٸ� ����ó��
								JOptionPane.showMessageDialog(null, "���ڸ� �Է��� �ּ���.", "message",JOptionPane.ERROR_MESSAGE);
							}else {
								result = checkChar(str.charAt(0));//�Է� ���ڸ� �������� �ƴ��� üũ�Ͽ� ������� �����´�.
							
								//���� Ƚ���� ���� ��� �̹����� ������ �ش�.
								switch(Hangman.failed) {
								case 1:
									remove(lbl3);
									lbl3 = new JLabel(img[1]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								case 2:
									remove(lbl3);
									lbl3 = new JLabel(img[2]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								case 3:
									remove(lbl3);
									lbl3 = new JLabel(img[3]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								case 4:
									remove(lbl3);
									lbl3 = new JLabel(img[4]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								case 5:
									remove(lbl3);
									lbl3 = new JLabel(img[5]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								case 6:
									remove(lbl3);
									lbl3 = new JLabel(img[6]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									break;
								}
								
								//��� ���� ���� �˸�â�� ����ش�.
								if(result==1) {
									JOptionPane.showMessageDialog(null, "������ϴ�.");
								}else if(result==2) {
									JOptionPane.showMessageDialog(null, "Ʋ�Ƚ��ϴ�.");
								}else if(result==3) {
									JOptionPane.showMessageDialog(null, "�̹� �Է��� �����Դϴ�. �ٽ� �Է��� �ּ���.");
								}
								
								remove(lbl4);
								lbl4 = new JLabel("���ڸ� �Է��ϼ���(���� ��ȸ " + (6-Hangman.failed) +") : ");
								lbl4.setFont(new Font(null, Font.BOLD, 15));
								lbl4.setBounds(100, 450, 300, 25);		
								hangmanGUI.this.add(lbl4);
								
								remove(lbl2);
								lbl2 = new JLabel(""+Hangman.outputString);
								lbl2.setFont(new Font(null, Font.BOLD, 25));
								lbl2.setBounds(300, 50, 200, 25);		
								hangmanGUI.this.add(lbl2);

								tf.setText("");
								revalidate();
								repaint();
								
								//��� ���乮�ڸ� ���߰ų� ��ȸ�� �������� ��� �˸�â�� ����ش�.
								if((Hangman.remaninder == 0)) {
									remove(lbl3);
									lbl3 = new JLabel(img[7]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									JOptionPane.showMessageDialog(null, "�����մϴ�.\n������ ���߼̽��ϴ�.");
								}else if((Hangman.failed == 6)) {
									JOptionPane.showMessageDialog(null, "�й��ϼ���.\n�����ϼ̽��ϴ�.");
								}
								
							}
						}
					}
				});
				this.add(btn);
				
				setSize(600, 600);
				// Windows�� ȭ�鿡 ���̰� �Ѵ�.
				setVisible(true);	
				
				
	}
	
	public int checkChar(char guess) {
		boolean already = false;
		for (int i = 0; i < Hangman.inputString.length(); i++) {
			if (Hangman.inputString.charAt(i) == guess) { // �̹��Է��ߴ� �������� ����
				already = true;
				return 3;
			}
		}

		if (!already) {
			// �Է��� ���ڵ��� ���ӿ� �߰�
			Hangman.inputString.append(guess);

			boolean success = false;
			for (int i = 0; i < Hangman.hiddenString.length(); i++) {
				if (Hangman.hiddenString.charAt(i) == guess) { // ������ �ش繮�ڰ� �ִ��� ����
					Hangman.outputString.setCharAt(i, guess); // ������ ���ڰ� ������ -�� ���ڷ� ����
					Hangman.remaninder--; // ���� ���ڼ� 1 ����
					success = true; // �Է��� ���ڰ� ������ �־����� ǥ��
				}
			}
			if (success){
				return 1;
			}else {
				Hangman.failed++; // �Է��� ���ڰ� ������ ������ ����Ƚ���� 1����
				return 2;
			}
		}
		return 0;
	}
	
}