package sanghee;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class hangmanGUI extends JFrame {
	hangmanGUI() {
		// Window 오른쪽 위의 [X]버튼을 누르면 프로그램이 종료되게 한다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("행맨 게임");
		// Windows의 크기를 지정한다. 가로500 X 세로500 픽셀크기로 지정했다.
		/////////////
		// 레이아웃을 FlowLayout으로 설정
		this.setLayout(null);
		// 이미지아이콘 1개를 준비한다 이는 15행과 19행에서 사용한다.
		ImageIcon img1 = new ImageIcon("image/hangman_img01.png");
		ImageIcon img2 = new ImageIcon("image/hangman_img02.png");
		ImageIcon img3 = new ImageIcon("image/hangman_img03.png");
		ImageIcon img4 = new ImageIcon("image/hangman_img04.png");
		ImageIcon img5 = new ImageIcon("image/hangman_img05.png");
		
		// 레이블에 문자 또는 이미지가 표현되도록 설정한다.
		JLabel lbl1 = new JLabel(img1);
		// 생성한 lbl1을 this(자체 윈도우)에 부착한다.
		this.add(lbl1);

		setSize(600, 600);
		// Windows가 화면에 보이게 한다.
		setVisible(true);
	}

	
}