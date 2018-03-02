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

import main.Main_GUI;
import main.Player;

public class PokerGUI  extends JFrame{
	
	private int comScore = 1000;
	
	public PokerGUI(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		JFrame frame = this;
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				frame.setVisible(false);	//서브 프레임이므로 X클릭 시, 보이지 않게 하고
				frame.dispose();// 자원을 회수
				new  Main_GUI();
			}
		});

		setTitle("포커게임");
		
		//이 부분에 버튼, 레이블, 체크박스 등을 코딩함
		//--------------------------------------------------------------
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(27, 133, 43));
		
		ImageIcon img1 = new ImageIcon("image/center.png");
		ImageIcon img2 = new ImageIcon("image/back.png");
		ImageIcon img3 = new ImageIcon("image/cProfile.png");
		ImageIcon img4 = new ImageIcon("image/pProfile.png");
		
		JLabel lbl1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(0).getSuit()+"-"+ comCards.get(0).getRank() + ".png"));
		lbl1.setBounds(30, 20, 156, 223);
		this.add(lbl1);	
		
		JLabel lbl2 = new JLabel(img2);
		lbl2.setBounds(221, 20, 156, 223);
		this.add(lbl2);
		
		JLabel lbl3 = new JLabel(img2);
		lbl3.setBounds(412, 20, 156, 223);
		this.add(lbl3);
		
		JLabel lbl4 = new JLabel(img2);
		lbl4.setBounds(603, 20, 156, 223);
		this.add(lbl4);
		
		JLabel lbl5 = new JLabel(img2);
		lbl5.setBounds(794, 20, 156, 223);
		this.add(lbl5);
				
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
		
		
		/*JLabel playerProfile = new JLabel("players 프로필");
		playerProfile.setBorder(new LineBorder(Color.red, 5));
		playerProfile.setOpaque(true);
		playerProfile.setBackground(Color.black);
		playerProfile.setBounds(30, 270, 180, 125);
		pn1.add(comProfile);*/
		
		JLabel lbl6 = new JLabel(img1);
		lbl6.setBounds(260, 270, 500, 250);
		this.add(lbl6);
		
		JPanel pn2 = new JPanel();
		pn2.setOpaque(false);
		pn2.setBounds(800, 270, 150, 250);
		this.add(pn2);
		
		JLabel lbl7 = new JLabel("베팅 점수 선택");
		lbl7.setFont(new Font(null, Font.BOLD, 20));
		pn2.add(lbl7);
		
		JRadioButton rdo1 = new JRadioButton("10점");
		rdo1.setPreferredSize(new Dimension(100, 35));
		rdo1.setOpaque(false);
		JRadioButton rdo2 = new JRadioButton("50점");
		rdo2.setPreferredSize(new Dimension(100, 35));
		rdo2.setOpaque(false);
		JRadioButton rdo3 = new JRadioButton("100점");
		rdo3.setPreferredSize(new Dimension(100, 35));
		rdo3.setOpaque(false);
		JRadioButton rdo4 = new JRadioButton("200점");
		rdo4.setPreferredSize(new Dimension(100, 35));
		rdo4.setOpaque(false);
		
		pn2.add(rdo1);	pn2.add(rdo2);	pn2.add(rdo3);	pn2.add(rdo4);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdo1);	grp.add(rdo2);	grp.add(rdo3);	grp.add(rdo4);
		
		JButton btn = new  JButton("결과 확인");
		btn.setPreferredSize(new Dimension(130, 35));
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
