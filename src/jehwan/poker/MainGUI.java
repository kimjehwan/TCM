package jehwan.poker;

import javax.swing.JFrame;

public class MainGUI extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*	MainGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("컴포넌트 연습2");
		
		//이 부분에 버튼, 레이블, 체크박스 등을 코딩함
		//--------------------------------------------------------------
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
				
		//--------------------------------------------------------------			
		setSize(1000, 800);
		setVisible(true);
	}
	*/
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
