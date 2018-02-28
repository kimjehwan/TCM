package jehwan.poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class MainGUI extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		MainGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("포커게임");
		
		//이 부분에 버튼, 레이블, 체크박스 등을 코딩함
		//--------------------------------------------------------------
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		this.getContentPane().setBackground(new Color(27, 133, 43));
		
		ImageIcon img1 = new ImageIcon("image/s_10.png");
		ImageIcon img2 = new ImageIcon("image/back.png");
		ImageIcon img3 = new ImageIcon("image/center.png");
		
		JLabel lbl1 = new JLabel(img1);
		this.add(lbl1);
		JLabel lbl2 = new JLabel(img2);
		this.add(lbl2);
		JLabel lbl3 = new JLabel(img2);
		this.add(lbl3);
		JLabel lbl4 = new JLabel(img2);
		this.add(lbl4);
		JLabel lbl5 = new JLabel(img2);
		this.add(lbl5);
		
		JLabel comProfile = new JLabel("컴퓨터 프로필");
		comProfile.setBorder(new LineBorder(Color.red, 5));
		comProfile.setPreferredSize(new Dimension(200,200));
		this.add(comProfile);
		
		JLabel lbl6 = new JLabel(img3);
		this.add(lbl6);
		
		JLabel lbl7 = new JLabel("베팅과 결과보기");
		this.add(lbl7);
		
		JLabel comProfile1 = new JLabel("플레이어 프로필");
		comProfile1.setBorder(new LineBorder(Color.red, 5));
		comProfile1.setPreferredSize(new Dimension(200,200));
		this.add(comProfile1);
		
		JLabel lbl11 = new JLabel(img1);
		this.add(lbl11);
		JLabel lbl12 = new JLabel(img1);
		this.add(lbl12);
		JLabel lbl13 = new JLabel(img1);
		this.add(lbl13);
		JLabel lbl14 = new JLabel(img1);
		this.add(lbl14);
		JLabel lbl15 = new JLabel(img1);
		this.add(lbl15);
		//--------------------------------------------------------------			
		setSize(1000, 800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		/*for(int i=0;i<100;i++) {
		System.out.println("============================");
		Deck deck = new Deck();
		deck.run();
		System.out.println("============================");
		}*/ //값 테스트 용 
		
		Deck deck = new Deck();
		deck.run();
		
		new MainGUI();
	}
}
