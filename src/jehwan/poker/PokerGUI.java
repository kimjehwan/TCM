package jehwan.poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class PokerGUI  extends JFrame{

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
		
		ImageIcon img1 = new ImageIcon("image/s_10.png");
		ImageIcon img2 = new ImageIcon("image/back.png");
		ImageIcon img3 = new ImageIcon("image/center.png");
		
		JLabel lbl1 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(0).getSuit()+"-"+ comCards.get(0).getRank() + ".png"));
		lbl1.setBounds(30, 20, 156, 223);
		this.add(lbl1);	
		
		JLabel lbl2 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(1).getSuit()+"-"+ comCards.get(1).getRank() + ".png"));
		lbl2.setBounds(221, 20, 156, 223);
		this.add(lbl2);
		
		JLabel lbl3 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(2).getSuit()+"-"+ comCards.get(2).getRank() + ".png"));
		lbl3.setBounds(412, 20, 156, 223);
		this.add(lbl3);
		
		JLabel lbl4 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(3).getSuit()+"-"+ comCards.get(3).getRank() + ".png"));
		lbl4.setBounds(603, 20, 156, 223);
		this.add(lbl4);
		
		JLabel lbl5 = new JLabel(new ImageIcon("image/poker/"+ comCards.get(4).getSuit()+"-"+ comCards.get(4).getRank() + ".png"));
		lbl5.setBounds(794, 20, 156, 223);
		this.add(lbl5);
		
		JLabel comProfile = new JLabel("컴퓨터 프로필");
		comProfile.setBorder(new LineBorder(Color.red, 5));
		comProfile.setOpaque(true);
		comProfile.setBackground(Color.WHITE);
		comProfile.setBounds(30, 270, 200, 239);
		this.add(comProfile);
		
		JLabel lbl6 = new JLabel(img3);
		lbl6.setBounds(260, 270, 300, 239);
		this.add(lbl6);
		
		JPanel pn = new JPanel();
		pn.setOpaque(false);
		pn.setBounds(580, 270, 150, 239);
		this.add(pn);
		
		JLabel lbl7 = new JLabel("베팅 점수 선택");
		lbl7.setFont(new Font(null, Font.BOLD, 20));
		pn.add(lbl7);
		
		JRadioButton rdo1 = new JRadioButton("10점");
		rdo1.setPreferredSize(new Dimension(100, 30));
		rdo1.setOpaque(false);
		JRadioButton rdo2 = new JRadioButton("50점");
		rdo2.setPreferredSize(new Dimension(100, 30));
		rdo2.setOpaque(false);
		JRadioButton rdo3 = new JRadioButton("100점");
		rdo3.setPreferredSize(new Dimension(100, 30));
		rdo3.setOpaque(false);
		JRadioButton rdo4 = new JRadioButton("200점");
		rdo4.setPreferredSize(new Dimension(100, 30));
		rdo4.setOpaque(false);
		
		pn.add(rdo1);	pn.add(rdo2);	pn.add(rdo3);	pn.add(rdo4);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdo1);	grp.add(rdo2);	grp.add(rdo3);	grp.add(rdo4);
		
		JButton btn = new  JButton("결과 확인");
		btn.setPreferredSize(new Dimension(130, 30));
		pn.add(btn);
		
		JLabel playerProfile = new JLabel("플레이어 프로필");
		playerProfile.setBorder(new LineBorder(Color.red, 5));
		playerProfile.setOpaque(true);
		playerProfile.setBackground(Color.WHITE);
		playerProfile.setBounds(750, 270, 200, 239);
		this.add(playerProfile);
		
		JLabel lbl8 = new JLabel(new ImageIcon("image/poker/"+ playerCards.get(0).getSuit()+"-"+ playerCards.get(0).getRank() + ".png"));
		lbl8.setBounds(30, 540, 156, 223);
		this.add(lbl8);	
		
		JLabel lbl9 = new JLabel(new ImageIcon("image/poker/"+ playerCards.get(1).getSuit()+"-"+ playerCards.get(1).getRank() + ".png"));
		lbl9.setBounds(221, 540, 156, 223);
		this.add(lbl9);
		
		JLabel lbl10 = new JLabel(new ImageIcon("image/poker/"+ playerCards.get(2).getSuit()+"-"+ playerCards.get(2).getRank() + ".png"));
		lbl10.setBounds(412, 540, 156, 223);
		this.add(lbl10);
		
		JLabel lbl11 = new JLabel(new ImageIcon("image/poker/"+ playerCards.get(3).getSuit()+"-"+ playerCards.get(3).getRank() + ".png"));
		lbl11.setBounds(603, 540, 156, 223);
		this.add(lbl11);
		
		JLabel lbl12 = new JLabel(new ImageIcon("image/poker/"+ playerCards.get(4).getSuit()+"-"+ playerCards.get(4).getRank() + ".png"));
		lbl12.setBounds(794, 540, 156, 223);
		this.add(lbl12);
	
		//--------------------------------------------------------------			
		setSize(1000, 800);
		setVisible(true);
	}

}
