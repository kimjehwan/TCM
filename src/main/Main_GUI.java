package main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jehwan.poker.PokerMain;
import taehwan.ExButton;

public class Main_GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Main_GUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("¸¶Ä«¿À¸¶Ä«¿À¸¶Ä«¿À");
		
		//ÀÌ ºÎºÐ¿¡ ¹öÆ°, ·¹ÀÌºí, Ã¼Å©¹Ú½º µîÀ» ÄÚµùÇÔ
		//--------------------------------------------------------------
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		ImageIcon img1 = new ImageIcon("image/casino.jpg");
		ImageIcon img2 = new ImageIcon("image/poker.png");
		ImageIcon img3 = new ImageIcon("image/gostop.png");
		ImageIcon img4 = new ImageIcon("image/blackjack.png");
		ImageIcon img5 = new ImageIcon("image/slot.jpg");

		
		JLabel lbl1 = new JLabel( img1);
		this.add(lbl1);
		
		JButton btn1 = new JButton("Æ÷Ä¿ ",img2);
		btn1.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn1.setPreferredSize(new Dimension( 180 , 70 ));
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn1) {
					ExButton MathQuiz = new ExButton();
					MathQuiz.showButton();
					setVisible(false);
				}
			}
		});
		this.add(btn1);
		
		JButton btn2 = new JButton(" ºí·¢Àè ",img4);
		btn2.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn2.setPreferredSize(new Dimension( 180 , 70 ));
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn2) {
					PokerMain poker = new PokerMain();
					poker.run();
					setVisible(false);
				}
			}
		});
		this.add(btn2);
		
		JButton btn3 = new JButton(" ¼¸´Ù ",img3);
		btn3.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn3.setPreferredSize(new Dimension( 180 , 70 ));
		this.add(btn3);
		
		JButton btn4 = new JButton(" ½½·Ô¸Ó½Å ",img5);
		btn4.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn4.setPreferredSize(new Dimension( 180 , 70 ));
		this.add(btn4);
		
		//--------------------------------------------------------------			
		setSize(420, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new  Main_GUI();
		Intro itr = new Intro();
		itr.intro();
		
	}
}