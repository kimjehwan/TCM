package main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.DBQuery;
import jehwan.poker.PokerMain;
import jihong.suttda.Suttda;
import sanghee.hangman.hangmanMain;
import taehwan.quiz.QuizMain;

public class MainGUI extends JFrame{
	public static JFrame frame; //프레임 전역변수 생성
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainGUI(){
		//지금 프레임을 전역변수로 지정한 변수에 지정
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TCM");
		
		//이 부분에 버튼, 레이블, 체크박스 등을 코딩함
		//--------------------------------------------------------------
		//레이아웃 없음
		this.setLayout(null);
		
		//이미지 아이콘 변수생성 및 대입
		ImageIcon img1 = new ImageIcon("image/casino.jpg");
		ImageIcon img2 = new ImageIcon("image/poker.png");
		ImageIcon img3 = new ImageIcon("image/gostop.png");
		ImageIcon img4 = new ImageIcon("image/blackjack.png");
		ImageIcon img5 = new ImageIcon("image/slot.jpg");

		//패널 생성
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		//메인 이미지를 라벨에 지정하고 라벨을 프레임에 삽입.
		JLabel lbl1 = new JLabel( img1);
		lbl1.setBounds(10, 10, 420, 190);
		this.add(lbl1);
		
		//아이디를 적는 텍스트 필드 지정
		JTextField jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(250, 30));
		panel1.add(new JLabel("I    D  : "));
		panel1.add(jtf);
		
		//비밀번호를 적는 패스워드 필드 지정
		JPasswordField jpf = new JPasswordField();
		jpf.setPreferredSize(new Dimension(250, 30));
		panel2.add(new JLabel("P  W : "));
		panel2.add(jpf);

		//로그인 버튼
		JButton signIn = new JButton("로 그 인");
		signIn.setPreferredSize(new Dimension(100, 30));
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//눌려진 버튼이 로그인 버튼이라면
				if((JButton)obj==signIn) {
					// 새로운 서브프레임 생성
					final Frame fs = new Frame("로그인");
					fs.setVisible(true);
					
					//db에서 데이터 체크
					DBQuery query = new DBQuery();
					boolean check = query.logIn(new String(jtf.getText()), new String(jpf.getPassword()), false);
					
					if(check) {
						//서브 프레임에 결과출력
						fs.add(new JLabel("로그인 성공"));
						//메인창의 로그인 패널 안보이게 함
						panel4.setVisible(false);
						//메인창에 로그인 정보를 출력
						JPanel panel4_1 = new JPanel();
						JLabel logInLbl = new JLabel(Player.getId()+"님, ");
						JLabel logInLb2 = new JLabel("반갑습니다.");
						panel4_1.add(logInLbl);
						panel4_1.add(logInLb2);
						
						logInLbl.setFont(new Font("맑은고딕", Font.BOLD, 30));
						logInLb2.setFont(new Font("맑은고딕", Font.BOLD, 30));
						panel4_1.setBounds(10, 210, 420, 150);
						frame.add(panel4_1);
						panel5.setVisible(true);
					}else {
						fs.add(new JLabel("로그인 실패"));
					}
					
					fs.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							fs.setVisible(false);
							fs.dispose();
						}
					});
					fs.setSize(250,150);
					fs.setLocation(100,200);
				}
			}
		});
		panel3.add(signIn);
		//회원가입 페이지로 넘어가는 버튼 값 제공
		JButton signUp = new JButton("회 원 가 입");
		signUp.setPreferredSize(new Dimension(100, 30));
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==signUp) {
					final Frame fs = new Frame("회원가입 결과");
					fs.setVisible(true);
					//아이디와 비밀번호는 1글자 이상 15이하로 한다.
					if(((jtf.getText().length())<=0 || (jtf.getText().length())>15) ||
							((jpf.getPassword().length)<=0 || (jpf.getPassword().length)>15))
					{
						JPanel p = new JPanel();
						p.add(new JLabel("아이디와 비밀번호는")); 
						p.add(new JLabel("영문 1~15자로 적어주세요.")); 
						fs.add(p);
					}else {
						DBQuery query = new DBQuery();
						boolean check = query.signUp(new String(jtf.getText()), new String(jpf.getPassword()), true);
						//DB에 중복된 값이 없다면 회원가입 성공
						if(check) {
							fs.add(new JLabel("회원가입 성공"));
						}else {	//있다면 아래의 문자를 출력한다.
							fs.add(new JLabel("해당 아이디가 이미 존재합니다."));
						}
					}
					//완료되면 회원가입 창을 닫는다.
					fs.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							fs.setVisible(false);
							fs.dispose();
						}
					});
					fs.setSize(250,150);
					fs.setLocation(100,200);
				}
			}
		});
		//여기서 부터는 게임으로 넘어가는 GUI이다.
		panel3.add(signUp);
		//패널을 제공한다.
		panel4.setBounds(10, 210, 420, 150);
		panel4.add(panel1);
		panel4.add(panel2);
		panel4.add(panel3);		
		this.add(panel4);		
		
		panel5.setLayout(new GridLayout(2, 2, 20, 20));
		panel5.setBounds(10, 360, 420, 190);
		panel5.setVisible(false);
		this.add(panel5);
		//버튼 1은 포커게임으로한다.
		JButton btn1 = new JButton("포커 ",img2);
		btn1.setFont(new Font("맑은고딕", Font.BOLD, 20));
		btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn1.setPreferredSize(new Dimension( 180 , 70 ));
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn1) {
					PokerMain poker = new PokerMain();
					poker.run();//포커게임을 시작한다.
					setVisible(false);//게임선택창을 숨긴다.
				}
			}
		});
		panel5.add(btn1);
		//버튼2는 퀴즈게임으로 설정한다.
		JButton btn2 = new JButton(" 퀴즈게임 ",img4);
		btn2.setFont(new Font("맑은고딕", Font.BOLD, 20));
		btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn2.setPreferredSize(new Dimension( 180 , 70 ));
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn2) {
					QuizMain b = new QuizMain();
					b.main(null);//퀴즈게임을 시작한다.
					setVisible(false);//게임선택창을 숨긴다.
				}
				
			}
		});
		panel5.add(btn2);
		//버튼3은 섯다게임으로 설정한다.
		JButton btn3 = new JButton(" 섯다 ",img3);
		btn3.setFont(new Font("맑은고딕", Font.BOLD, 20));
		btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn3.setPreferredSize(new Dimension( 180 , 70 ));
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn3) {
					Suttda.play();//섯다게임을 시작한다.
					setVisible(false);//게임선택창을 숨긴다.
				}
			}
		});
		panel5.add(btn3);
		//버튼 4는 행맨게임으로 설정한다.
		JButton btn4 = new JButton(" 행맨게임 ",img5);
		btn4.setFont(new Font("맑은고딕", Font.BOLD, 20));
		btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn4.setPreferredSize(new Dimension( 180 , 70 ));
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if((JButton)obj==btn4) {
					hangmanMain.run();//행맨게임을 시작한다.
					setVisible(false);//게임선택창을 숨긴다.
				}
			}
		});
		panel5.add(btn4);
		
		//--------------------------------------------------------------			
		setSize(455, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//GUI메인으로 부르는 생성자를 생성한다.
		new  MainGUI();
		Intro itr = new Intro();
		itr.intro();
	}
}