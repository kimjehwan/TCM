package jihong;

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

import main.Main_GUI;

public class Project_suttda_GuI extends JFrame{
		
		void GuI(int playerCard[], int comCard[]) {
			JFrame frame =this;
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					frame.setVisible(false);
					frame.dispose();
					Main_GUI.frame.setVisible(true);
				}
			});
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("인생은 한방 섯다");
			/////////////////////////////////////////////////////////////////////////////////
			
			this.getContentPane().setBackground(new Color(27, 133, 43));
			
			this.setLayout(null);
			
			//btn1의 버튼 이벤트
			JButton btn1 = new JButton("");
			btn1.setBounds(100,30,87,130);
			btn1.setBackground(new Color(227,53,57));
			btn1.setVisible(true);
			
			JButton btn11 = new JButton(new ImageIcon("image/suttda/" + playerCard[0] + ".jpg"));
			btn11.setBounds(100,30,87,130);			
			btn11.setVisible(false);
			this.add(btn11);
						
			this.add(btn1);
			
			//btn2의 버튼 이벤트
			JButton btn2 = new JButton("");
			btn2.setBounds(200,30,87,130);
			btn2.setBackground(new Color(227,53,57));
			btn2.setVisible(true);
			
			JButton btn22 = new JButton(new ImageIcon("image/suttda/" + playerCard[1] + ".jpg"));
			btn22.setBounds(200,30,87,130);		
			btn22.setVisible(false);
			this.add(btn22);
			
			this.add(btn2);
			
			JButton btn3 = new JButton("");
			btn3.setBounds(100,270,87,130);
			btn3.setBackground(new Color(227,53,57));
			btn3.setVisible(true);
		
			JButton btn33 = new JButton(new ImageIcon("image/suttda/" + comCard[0] + ".jpg"));
			btn33.setBounds(100,270,87,130);
			btn33.setVisible(false);
			this.add(btn33);
			
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btn3.setVisible(false);btn33.setVisible(true);
				}
			});
			this.add(btn3);
			
			JButton btn4 = new JButton("");
			btn4.setBounds(200,270,87,130);
			btn4.setBackground(new Color(227,53,57));
			btn4.setVisible(true);
			
			JButton btn44 = new JButton(new ImageIcon("image/suttda/" + comCard[1] + ".jpg"));
			btn44.setBounds(200,270,87,130);
			this.add(btn44);
			btn44.setVisible(false);
			
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btn4.setVisible(false);btn44.setVisible(true);
				}
			});
			this.add(btn4);
			
			JButton btn5 = new JButton(" 패뒤집기 ");
			btn5.setBounds(140,210,100,30);
			this.add(btn5);
						
			
			JLabel lb1 = new JLabel("player"); 
			lb1.setBounds(165,415,100,30);
			lb1.setFont(new Font(null,Font.BOLD, 20));
			this.add(lb1);
			
						
			//////////////////////////////////////////////////////////////////////////
			setSize(400,500);
			this.setLocationRelativeTo(null);
			setVisible(true);
			setResizable(false);
			
			btn5.addActionListener(new ActionListener()  {
				public void actionPerformed(ActionEvent arg0) {
					btn1.setVisible(false);btn11.setVisible(true);
					btn2.setVisible(false);btn22.setVisible(true);
					System.out.println("창 뜨기");
					if(abc.result == 1) {
						String[] buttonName = {"네", "아니오"};						
						int flag = JOptionPane.showConfirmDialog(null, "비겼습니다 \n 다시하시겠습니까?", "결과", JOptionPane.YES_NO_OPTION);
							if(flag == JOptionPane.YES_OPTION) {
								System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
								dispose();
								new abc();

								
							}else if(flag == JOptionPane.CLOSED_OPTION){
							} else {
								dispose();
								Main_GUI.frame.setVisible(true);
							}
						
					}
					
					else if(abc.result == 2) {
						String[] buttonName = {"네", "아니오"};						
						int flag = JOptionPane.showConfirmDialog(null, "비겼습니다 \n 다시하시겠습니까?", "결과", JOptionPane.YES_NO_OPTION);
							if(flag == JOptionPane.YES_OPTION) {
								dispose();
								new abc();
						
							}else if(flag == JOptionPane.CLOSED_OPTION){
							} else {
								dispose();
								Main_GUI.frame.setVisible(true);
							}
							
					}else if(abc.result == 3) {
						String[] buttonName = {"네", "아니오"};						
						int flag = JOptionPane.showConfirmDialog(null, "비겼습니다 \n 다시하시겠습니까?", "결과", JOptionPane.YES_NO_OPTION);
							if(flag == JOptionPane.YES_OPTION) {
								dispose();
								new abc();
					
							}else if(flag == JOptionPane.CLOSED_OPTION){
							} else {
								dispose();
								Main_GUI.frame.setVisible(true);
								}
					}
				}
			});
			
		}
	}
	
	
