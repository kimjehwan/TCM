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
	
	JLabel lbl2;	//정답맞춘 수에 따라 달라지는 outputString을 표시할 레이블 변수
	JLabel lbl3;	//행맨 이미지를 담을 레이블 변수
	JLabel lbl4;	//문자를 입력할 수 있는 안내 문자열을 담을 레이블 변수
	ImageIcon[] img = new ImageIcon[8];	//이미지아이콘들을 담을 수 있는 배열 변수
	int result;	 //정답과 오답, 이미 입력한 문자인지를 확인할 수 있는 값을 담은 변수
	
	public hangmanGUI() {
	
		JFrame frame =this;
		frame.addWindowListener(new WindowAdapter() {
			// Window 오른쪽 위의 [X]버튼을 누르면
			public void windowClosing(WindowEvent windowEvent) {
				frame.setVisible(false);
				frame.dispose();
				MainGUI.frame.setVisible(true);
				//기존 행맨게임 자원을 회수하고 메인프레임을 보여준다.
			}
		});
				this.getContentPane().setBackground(Color.white);
				setTitle("행맨 게임");
				this.setLayout(null);
				
				//이미지들을 이미지 배열 변수에 담아 준다.
				img[0] = 	new ImageIcon("image/image/hangman_0.png");
				img[1] = new ImageIcon("image/image/hangman_1.png");
				img[2] = new ImageIcon("image/image/hangman_2.png");
				img[3] = new ImageIcon("image/image/hangman_3.png");
				img[4] = new ImageIcon("image/image/hangman_4.png");
				img[5] = new ImageIcon("image/image/hangman_5.png");
				img[6] = new ImageIcon("image/image/hangman_6.png");
				img[7] = new ImageIcon("image/image/hangman_7.png");
				
				//정답 글자수를 알려주는 문자열을 담은 레이블 변수
				JLabel lbl1 = new JLabel("단어 (" + Hangman.outputString.length() + "글자) :");
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
				

				lbl4 = new JLabel("문자를 입력하세요(남은 기회 " + (6-Hangman.failed) +") : ");
				lbl4.setFont(new Font(null, Font.BOLD, 15));
				lbl4.setBounds(100, 450, 300, 25);		
				this.add(lbl4);
							
				//유저가 입력할 수 있는 입력창을 나타내는 변수
				JTextField tf = new JTextField();
				tf.setBounds(325, 450, 50, 25);	
				this.add(tf);
				
				//입력버튼을 나타내는 변수
				JButton btn = new JButton("입력");
				btn.setBounds(400, 450, 60, 25);	
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Object obj = e.getSource();
						if((JButton)obj==btn) {
							String str = tf.getText();
							if(str.equals("")) {//만약 입력문자가 없다면 예외처리
								JOptionPane.showMessageDialog(null, "문자를 입력해 주세요.", "message",JOptionPane.ERROR_MESSAGE);
							}else {
								result = checkChar(str.charAt(0));//입력 문자를 정답인지 아닌지 체크하여 결과값을 가져온다.
							
								//실패 횟수에 따라 행맨 이미지를 변경해 준다.
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
								
								//결과 값에 따라 알림창을 띄워준다.
								if(result==1) {
									JOptionPane.showMessageDialog(null, "맞췄습니다.");
								}else if(result==2) {
									JOptionPane.showMessageDialog(null, "틀렸습니다.");
								}else if(result==3) {
									JOptionPane.showMessageDialog(null, "이미 입력한 문자입니다. 다시 입력해 주세요.");
								}
								
								remove(lbl4);
								lbl4 = new JLabel("문자를 입력하세요(남은 기회 " + (6-Hangman.failed) +") : ");
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
								
								//모든 정답문자를 맞추거나 기회를 소진했을 경우 알림창을 띄워준다.
								if((Hangman.remaninder == 0)) {
									remove(lbl3);
									lbl3 = new JLabel(img[7]);
									lbl3.setBounds(210, 150, 163, 211);		
									hangmanGUI.this.add(lbl3);
									JOptionPane.showMessageDialog(null, "축하합니다.\n정답을 맞추셨습니다.");
								}else if((Hangman.failed == 6)) {
									JOptionPane.showMessageDialog(null, "분발하세요.\n실패하셨습니다.");
								}
								
							}
						}
					}
				});
				this.add(btn);
				
				setSize(600, 600);
				// Windows가 화면에 보이게 한다.
				setVisible(true);	
				
				
	}
	
	public int checkChar(char guess) {
		boolean already = false;
		for (int i = 0; i < Hangman.inputString.length(); i++) {
			if (Hangman.inputString.charAt(i) == guess) { // 이미입력했던 문자인지 조사
				already = true;
				return 3;
			}
		}

		if (!already) {
			// 입력한 문자들의 모임에 추가
			Hangman.inputString.append(guess);

			boolean success = false;
			for (int i = 0; i < Hangman.hiddenString.length(); i++) {
				if (Hangman.hiddenString.charAt(i) == guess) { // 문제애 해당문자가 있는지 조사
					Hangman.outputString.setCharAt(i, guess); // 문제에 문자가 있으면 -를 문자로 변경
					Hangman.remaninder--; // 맞출 문자수 1 감소
					success = true; // 입력한 문자가 문제에 있었음을 표시
				}
			}
			if (success){
				return 1;
			}else {
				Hangman.failed++; // 입력한 문자가 문제가 없으면 실패횟수를 1증가
				return 2;
			}
		}
		return 0;
	}
	
}