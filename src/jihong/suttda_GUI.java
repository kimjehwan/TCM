package jihong;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class suttda_GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	suttda_GUI(int playerCard[],int comCard[]) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("인생은 한방 섯다");
			////////////////////////////////////////////////////////////////
			
			this.getContentPane().setBackground(new Color(27, 133, 43));
			this.setLayout(null);
						
			JButton btn1 = new JButton(new ImageIcon("image/suttda/"+ playerCard[0] +".jpg"));
			btn1.setBounds(100, 30, 87, 130);
			btn1.setBackground(new Color(194, 24, 71));
			this.add(btn1);
			
			JButton btn2 = new JButton(new ImageIcon("image/suttda/"+ playerCard[1] +".jpg"));
			btn2.setBounds(200, 30, 87, 130);
			btn2.setBackground(new Color(194, 24, 71));
			this.add(btn2);
			
			JButton btn3 = new JButton(new ImageIcon("image/suttda/"+ comCard[0] +".jpg"));
			btn3.setBounds(100, 260, 87, 130);
			btn3.setBackground(new Color(194, 24, 71));
			this.add(btn3);
			
			JButton btn4 = new JButton(new ImageIcon("image/suttda/"+ comCard[1] +".jpg"));
			btn4.setBounds(200, 260, 87, 130);
			btn4.setBackground(new Color(194, 24, 71));
			this.add(btn4);
			///////////////////////////////////////////////////////////////
			setSize(400, 500);
			setVisible(true);
		}
		
	
}