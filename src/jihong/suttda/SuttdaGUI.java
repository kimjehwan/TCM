package jihong.suttda;

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

import DB.DBQuery;
import main.MainGUI;

public class SuttdaGUI extends JFrame {
	static int sc = 1000;
	static int radioScore = 100;
	
	SuttdaGUI(int playerCard[], int comCard[]) {
		
		
		System.out.println(Suttda.result);
		this.addWindowListener(new WindowAdapter() {	
			public void windowClosing(WindowEvent windowEvent) {	//우측 상단의 X버튼을 누르면
				DBQuery.savePoker();//포커 점수를 DB에 저장
				SuttdaGUI.this.setVisible(false);	//서브 프레임이므로 X클릭 시, 보이지 않게 하고
				SuttdaGUI.this.dispose();// 자원을 회수
				MainGUI.frame.setVisible(true);	//숨겨 뒀던 메인프레임을 다시 보여준다
			}
		});
		setTitle("인생은 한방 섯다");
		/////////////////////////////////////////////////////////////////////////////////

		this.getContentPane().setBackground(new Color(27, 133, 43));
		this.setLayout(null);

		// btn1의 버튼 이벤트
		JButton btn1 = new JButton("");
		btn1.setBounds(100, 30, 87, 130);
		btn1.setBackground(new Color(227, 53, 57));
		btn1.setVisible(true);

		JButton btn11 = new JButton(new ImageIcon("image/suttda/" + comCard[0] + ".jpg"));
		btn11.setBounds(100, 30, 87, 130);
		btn11.setVisible(false);
		this.add(btn11);

		this.add(btn1);

		// btn2의 버튼 이벤트
		JButton btn2 = new JButton("");
		btn2.setBounds(200, 30, 87, 130);
		btn2.setBackground(new Color(227, 53, 57));
		btn2.setVisible(true);

		JButton btn22 = new JButton(new ImageIcon("image/suttda/" + comCard[1] + ".jpg"));
		btn22.setBounds(200, 30, 87, 130);
		btn22.setVisible(false);
		this.add(btn22);

		this.add(btn2);

		JButton btn3 = new JButton("");
		btn3.setBounds(100, 270, 87, 130);
		btn3.setBackground(new Color(227, 53, 57));
		btn3.setVisible(true);

		JButton btn33 = new JButton(new ImageIcon("image/suttda/" + playerCard[0] + ".jpg"));
		btn33.setBounds(100, 270, 87, 130);
		btn33.setVisible(false);
		this.add(btn33);

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn3.setVisible(false);
				btn33.setVisible(true);
			}
		});
		this.add(btn3);

		JButton btn4 = new JButton("");
		btn4.setBounds(200, 270, 87, 130);
		btn4.setBackground(new Color(227, 53, 57));
		btn4.setVisible(true);

		JButton btn44 = new JButton(new ImageIcon("image/suttda/" + playerCard[1] + ".jpg"));
		btn44.setBounds(200, 270, 87, 130);
		this.add(btn44);
		btn44.setVisible(false);

		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn4.setVisible(false);
				btn44.setVisible(true);
			}
		});
		this.add(btn4);

		JButton btn5 = new JButton(" 패뒤집기 ");
		btn5.setBounds(140, 210, 100, 30);
		this.add(btn5);
		
		//데이터베이스에 저장된 플레이어 이름 가져온다.
		JLabel lb1 = new JLabel("player");
		lb1.setBounds(165, 415, 100, 30);
		lb1.setFont(new Font(null, Font.BOLD, 20));
		this.add(lb1);
		
		JLabel lb2 = new JLabel("점수 : " + sc);
		lb2.setBounds(230,415,100,30);
		lb2.setFont(new Font(null, Font.BOLD, 15));
		this.add(lb2);
		
		//////////////////////////////////////////////////////////////////////////
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setVisible(false);
				btn11.setVisible(true);
				btn2.setVisible(false);
				btn22.setVisible(true);
				System.out.println("창 뜨기");
				//이겼을 경우 액션
				if (Suttda.result == 1) {
					String[] buttonName = { "네", "아니오" };
					int flag = JOptionPane.showConfirmDialog(null, "컴퓨터 : " + comCard[0]%10 + "월" + comCard[1]%10 + "월\n"  
							+ "당신 : " + playerCard[0]%10 + "월" + playerCard[1]%10 + "월\n"  + "이겼습니다 \n 다시하시겠습니까?", "결과", 
							JOptionPane.YES_NO_OPTION);		// 상대방과 본인의 패 확인 + 결과확인		 
					if (flag == JOptionPane.YES_OPTION) {
						
						dispose();// 고스톱 창 디스포즈
						Suttda Suttda = new Suttda();
						Suttda.play();

					} else if (flag == JOptionPane.CLOSED_OPTION) {
					} else {
						dispose();
						MainGUI.frame.setVisible(true);
					}
					;
				}else if (Suttda.result == 2) {
					String[] buttonName = { "네", "아니오" };
					int flag = JOptionPane.showConfirmDialog(null, "컴퓨터 : " + comCard[0]%10 + "월" + comCard[1]%10 + "월\n"  
							+ "당신 : " + playerCard[0]%10 + "월" + playerCard[1]%10 + "월\n"  +  "비겼습니다 \n 다시하시겠습니까?", "결과",
							JOptionPane.YES_NO_OPTION);  // 상대방과 본인의 패 확인 + 결과확인	
					if (flag == JOptionPane.YES_OPTION) {
						dispose();// 고스톱 창 디스포즈
						Suttda Suttda = new Suttda();
						Suttda.play();
					} else if (flag == JOptionPane.CLOSED_OPTION) {
					} else {
						dispose();
						MainGUI.frame.setVisible(true);
					}

				} else if (Suttda.result == 3) {
					String[] buttonName = { "네", "아니오" };
					int flag = JOptionPane.showConfirmDialog(null, "컴퓨터 : " + comCard[0]%10 + "월" + comCard[1]%10 + "월\n"  
							+ "당신 : " + playerCard[0]%10 + "월" + playerCard[1]%10 + "월\n"  +  "졌습니다 \n 다시하시겠습니까?", "결과", 
							JOptionPane.YES_NO_OPTION);			 // 상대방과 본인의 패 확인	+ 결과확인	
					if (flag == JOptionPane.YES_OPTION) {
						dispose();// 고스톱 창 디스포즈
						Suttda Suttda = new Suttda();
						Suttda.play();
					} else if (flag == JOptionPane.CLOSED_OPTION) {
					} else {
						dispose();
						MainGUI.frame.setVisible(true);
					}
					
				}
			}
		});
		setSize(400, 500);
		this.setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
