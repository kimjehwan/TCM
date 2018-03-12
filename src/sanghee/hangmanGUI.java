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
		
		// Window 오른쪽 위의 [X]버튼을 누르면 프로그램이 종료되게 한다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		setTitle("행맨 게임");
		// Windows의 크기를 지정한다. 가로500 X 세로500 픽셀크기로 지정했다.
		/////////////
		// 레이아웃을 FlowLayout으로 설정
		this.setLayout(null);
		// 이미지아이콘 1개를 준비한다 이는 15행과 19행에서 사용한다.
		ImageIcon img1 = new ImageIcon("image/image/hangman_01.png");
		ImageIcon img2 = new ImageIcon("image/image/hangman_02.png");
		ImageIcon img3 = new ImageIcon("image/image/hangman_03.png");
		ImageIcon img4 = new ImageIcon("image/image/hangman_img04.png");
		ImageIcon img5 = new ImageIcon("image/image/hangman_img05.png");
		
		JLabel lbl1 = new JLabel("단어 (" + outputString.length() + "글자) :");
		lbl1.setFont(new Font(null, Font.BOLD, 25));
		lbl1.setBounds(130, 50, 170, 25);		
		this.add(lbl1);

		JLabel lbl2 = new JLabel(""+outputString);
		lbl2.setFont(new Font(null, Font.BOLD, 25));
		lbl2.setBounds(300, 50, 200, 25);		
		this.add(lbl2);
		
		// 레이블에 문자 또는 이미지가 표현되도록 설정한다.
		JLabel lbl3 = new JLabel(img1);
		lbl3.setBounds(210, 150, 163, 211);		
		this.add(lbl3);
		
		JLabel lbl4 = new JLabel("문자를 입력하세요(남은 기회 " + (6-Hangman.failed) +") : ");
		lbl4.setFont(new Font(null, Font.BOLD, 15));
		lbl4.setBounds(100, 450, 300, 25);		
		this.add(lbl4);
		
		JTextField tf = new JTextField();
		tf.setBounds(325, 450, 50, 25);	
		tf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				//눌린 키의 정수값을 key변수에 저장한다.
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
					JLabel lbl4 = new JLabel("문자를 입력하세요(남은 기회 " + (6-Hangman.failed) +") : ");
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
				/////////// 눌린키가 엔터라면 텍스트 필드의 내용을 텍스트영역에 추가. 줄바꿈. 텍스트필드 지움.
			}
		});
		this.add(tf);

		JButton btn = new JButton("입력");
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
		// Windows가 화면에 보이게 한다.
		setVisible(true);
	}

	
}