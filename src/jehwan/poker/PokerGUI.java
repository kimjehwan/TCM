package jehwan.poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import DB.DBQuery;
import main.Main_GUI;
import main.Player;

public class PokerGUI  extends JFrame{
	
	private int comScore = 1000;
	private int radioScore;
	
	public PokerGUI(ArrayList<Card> playerCards, ArrayList<Card> comCards,String winnerDeck, String winner, int winnerCheck) {
		JFrame frame = this;
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				DBQuery.savePoker();
				frame.setVisible(false);	//서브 프레임이므로 X클릭 시, 보이지 않게 하고
				frame.dispose();// 자원을 회수
				Main_GUI.frame.setVisible(true);
				//new  Main_GUI();
			}
		});

		setTitle("포커게임");
		
		//이 부분에 버튼, 레이블, 체크박스 등을 코딩함
		//--------------------------------------------------------------
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(27, 133, 43));
		
		ImageIcon img1 = new ImageIcon("image/center.png");
		ImageIcon img2 = new ImageIcon("image/back.png");
		
		JLabel lbl1 = new JLabel(img2);
		lbl1.setBounds(30, 20, 156, 223);
		this.add(lbl1);	
		JLabel lbl1_1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(0).getSuit()+"-"+ comCards.get(0).getRank() + ".png"));
		lbl1_1.setBounds(30, 20, 156, 223);
		lbl1_1.setVisible(false);
		this.add(lbl1_1);
		
		JLabel lbl2 = new JLabel(img2);
		lbl2.setBounds(221, 20, 156, 223);
		this.add(lbl2);
		JLabel lbl2_1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(1).getSuit()+"-"+ comCards.get(1).getRank() + ".png"));
		lbl2_1.setBounds(221, 20, 156, 223);
		lbl2_1.setVisible(false);
		this.add(lbl2_1);
		
		JLabel lbl3 = new JLabel(img2);
		lbl3.setBounds(412, 20, 156, 223);
		this.add(lbl3);
		JLabel lbl3_1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(2).getSuit()+"-"+ comCards.get(2).getRank() + ".png"));
		lbl3_1.setBounds(412, 20, 156, 223);
		lbl3_1.setVisible(false);
		this.add(lbl3_1);
		
		JLabel lbl4 = new JLabel(img2);
		lbl4.setBounds(603, 20, 156, 223);
		this.add(lbl4);
		JLabel lbl4_1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(3).getSuit()+"-"+ comCards.get(3).getRank() + ".png"));
		lbl4_1.setBounds(603, 20, 156, 223);
		lbl4_1.setVisible(false);
		this.add(lbl4_1);
		
		JLabel lbl5 = new JLabel(img2);
		lbl5.setBounds(794, 20, 156, 223);
		this.add(lbl5);
		JLabel lbl5_1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(4).getSuit()+"-"+ comCards.get(4).getRank() + ".png"));
		lbl5_1.setBounds(794, 20, 156, 223);
		lbl5_1.setVisible(false);
		this.add(lbl5_1);
				
		JPanel cProfile = new JPanel();
		cProfile.setBorder(new LineBorder(Color.red, 5));
		cProfile.setBounds(30, 270, 200, 100);
		this.add(cProfile);
		
		JLabel cName = new JLabel("Computer 님 \n");
		cName.setPreferredSize(new Dimension(180, 40));
		cName.setFont(new Font(null, Font.BOLD, 20));
		JLabel cScore = new JLabel("점수  :    " + comScore + "점");
		cScore.setPreferredSize(new Dimension(180, 40));
		cScore.setFont(new Font("맑은고딕", Font.BOLD, 20));
		cProfile.add(cName);
		cProfile.add(cScore);
		
		JPanel pProfile = new JPanel();
		pProfile.setBorder(new LineBorder(Color.red, 5));
		pProfile.setBounds(30, 420, 200, 100);
		this.add(pProfile);
		
		JLabel pName = new JLabel(Player.getId() + " 님 \n");
		pName.setPreferredSize(new Dimension(180, 40));
		pName.setFont(new Font(null, Font.BOLD, 20));
		JLabel pScore = new JLabel("점수  :    " + Player.getpScore() + "점");
		pScore.setPreferredSize(new Dimension(180, 40));
		pScore.setFont(new Font("맑은고딕", Font.BOLD, 20));
		pProfile.add(pName);
		pProfile.add(pScore);
		
		JPanel center = new JPanel();
		center.setOpaque(false);
		center.setBounds(260, 270, 500, 250);
		this.add(center);
		
		
		JLabel lbl6 = new JLabel(img1);
		center.add(lbl6);
		JLabel lbl6_1 = new JLabel("      " + winner + "      ");
		JLabel lbl6_2 = new JLabel("      " + winnerDeck + "      ");
		

		lbl6_1.setFont(new Font(null, Font.BOLD, 40));
		lbl6_2.setFont(new Font(null, Font.BOLD, 30));
		lbl6_1.setVisible(false);
		lbl6_2.setVisible(false);
		center.add(lbl6_1);
		center.add(lbl6_2);
		
		JPanel pn2 = new JPanel();
		pn2.setOpaque(false);
		pn2.setBounds(800, 270, 150, 250);
		this.add(pn2);
		
		JLabel lbl7 = new JLabel("베팅 점수 선택");
		lbl7.setFont(new Font(null, Font.BOLD, 20));
		pn2.add(lbl7);
		
		JRadioButton rdo1 = new JRadioButton("10",true);
		rdo1.setPreferredSize(new Dimension(100, 35));
		rdo1.setOpaque(false);
		JRadioButton rdo2 = new JRadioButton("50");
		rdo2.setPreferredSize(new Dimension(100, 35));
		rdo2.setOpaque(false);
		JRadioButton rdo3 = new JRadioButton("100");
		rdo3.setPreferredSize(new Dimension(100, 35));
		rdo3.setOpaque(false);
		JRadioButton rdo4 = new JRadioButton("200");
		rdo4.setPreferredSize(new Dimension(100, 35));
		rdo4.setOpaque(false);
		
		pn2.add(rdo1);	pn2.add(rdo2);	pn2.add(rdo3);	pn2.add(rdo4);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdo1);	grp.add(rdo2);	grp.add(rdo3);	grp.add(rdo4);
		
		 JButton btn1 = new  JButton("다시하기");
		 btn1.setPreferredSize(new Dimension(130, 35));
		 btn1.setVisible(false);
		 btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					if((JButton)obj==btn1) {
						setVisible(false);
						dispose();
						PokerMain.run();
					}
				}
			});
		 pn2.add(btn1);
		
		JButton btn = new  JButton("결과 확인");
		btn.setPreferredSize(new Dimension(130, 35));
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn) {
					
					lbl6.setVisible(false);lbl6_1.setVisible(true);lbl6_2.setVisible(true);
					
					lbl1.setVisible(false);lbl1_1.setVisible(true);
					lbl2.setVisible(false);lbl2_1.setVisible(true);
					lbl3.setVisible(false);lbl3_1.setVisible(true);
					lbl4.setVisible(false);lbl4_1.setVisible(true);
					lbl5.setVisible(false);lbl5_1.setVisible(true);
					btn.setVisible(false);btn1.setVisible(true);
					
					if(rdo1.isSelected()) {
						radioScore = 10;
					}else if(rdo2.isSelected()) {
						radioScore = 50;
					}else if(rdo3.isSelected()) {
						radioScore = 100;
					}else if(rdo4.isSelected()) {
						radioScore = 200;
					}
					
					if(winnerCheck==1) {
						Player.setpScore(Player.getpScore() + radioScore);
						JLabel lbl6_3 = new JLabel("플레이어 점수  :   + " + radioScore);
						lbl6_3.setFont(new Font(null, Font.BOLD, 30));
						center.add(lbl6_3);
					}else if(winnerCheck==0) {
						Player.setpScore(Player.getpScore() - radioScore);
						JLabel lbl6_4 = new JLabel("플레이어 점수  :   - " + radioScore);
						lbl6_4.setFont(new Font(null, Font.BOLD, 30));
						center.add(lbl6_4);
					}
					
				}
			}
		});
		pn2.add(btn);
		
		
		JButton lbl8 = new JButton(new ImageIcon("image/poker/"+ playerCards.get(0).getSuit()+"-"+ playerCards.get(0).getRank() + ".png"));
		lbl8.setBounds(30, 540, 156, 223);
		this.add(lbl8);	
	
		
		JButton lbl9 = new JButton(img2);
		lbl9.setBounds(221, 540, 156, 223);
		lbl9.setToolTipText("더블클릭 하세요~");
		this.add(lbl9);
		lbl9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(lbl9);
				JButton lbl91 = new JButton(new ImageIcon("image/poker/"+ playerCards.get(1).getSuit()+"-"+ playerCards.get(1).getRank() + ".png"));
				lbl91.setBounds(221, 540, 156, 223);
				frame.add(lbl91);
			}
		});
		
		JButton lbl10 = new JButton(img2);
		lbl10.setBounds(412, 540, 156, 223);
		lbl10.setToolTipText("더블클릭 하세요~");
		this.add(lbl10);
		lbl10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(lbl10);
				JButton lbl101 = new JButton(new ImageIcon("image/poker/"+ playerCards.get(2).getSuit()+"-"+ playerCards.get(2).getRank() + ".png"));
				lbl101.setBounds(412, 540, 156, 223);
				frame.add(lbl101);
			}
		});
		
		JButton lbl11 = new JButton(img2);
		lbl11.setBounds(603, 540, 156, 223);
		lbl11.setToolTipText("더블클릭 하세요~");
		this.add(lbl11);
		lbl11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(lbl11);
				JButton lbl111 = new JButton(new ImageIcon("image/poker/"+ playerCards.get(3).getSuit()+"-"+ playerCards.get(3).getRank() + ".png"));
				lbl111.setBounds(603, 540, 156, 223);
				frame.add(lbl111);
			}
		});
		
		JButton lbl12 = new JButton(img2);
		lbl12.setBounds(794, 540, 156, 223);
		lbl12.setToolTipText("더블클릭 하세요~");
		this.add(lbl12);
		lbl12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(lbl12);
				JButton lbl121 = new JButton(new ImageIcon("image/poker/"+ playerCards.get(4).getSuit()+"-"+ playerCards.get(4).getRank() + ".png"));
				lbl121.setBounds(794, 540, 156, 223);
				frame.add(lbl121);
			}
		});
	
		//--------------------------------------------------------------			
		setSize(1000, 820);
		setVisible(true);
	}

}
