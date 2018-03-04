package sanghee;


/*
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class hangmanGUI extends JFrame {
	hangmanGUI() {
		// Window 오른쪽 위의 [X]버튼을 누르면 프로그램이 종료되게 한다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("게임");
		// Windows의 크기를 지정한다. 가로500 X 세로500 픽셀크기로 지정했다.
		/////////////
		// 레이아웃을 FlowLayout으로 설정
		this.setLayout(new FlowLayout());
		// 이미지아이콘 1개를 준비한다 이는 15행과 19행에서 사용한다.
		ImageIcon img1 = new ImageIcon("image/hangman_img05.png");
		// 레이블에 문자 또는 이미지가 표현되도록 설정한다.
		JLabel lbl1 = new JLabel(img1);
		// 생성한 lbl1을 this(자체 윈도우)에 부착한다.
		this.add(lbl1);

		setSize(600, 600);
		// Windows가 화면에 보이게 한다.
		setVisible(true);
	}

	// 메인()메소드에서 MyGUI 생성자가 실행되므로 Windows창이 출력된다
	public static void main(String[] args) {
		new hangmanGUI();
	}
}

// main()메소드에서 MyGUI 클래스를 생성한다.
// 결국 행의 MyGUI()생성자가 실행되므로 Window창이 출력된다.
// 메인메소드 시작


 * import javax.swing.JFrame;
 * 
 * public class hangmanGUI { static class MyGUI extends JFrame{ MyGUI(){
 * //Window 오른쪽 위의 [X]버튼을 누르면 프로그램이 종료되게 한다.
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setTitle("행맨게임");
 * ///////////////////////////////////////////
 * 
 * 
 * /////////////////////////////////////////// //Windows의 크기를 지정한다. 가로500 X
 * 세로500 픽셀크기로 지정했다. setSize(600,600); //Windows가 화면에 보이게 한다. setVisible(true);
 * } } }
 
*/

import java.util.*;
import java.io.*;

public class hangmanGUI {
	int HIDDENCHAR; // 숨기는 글자 개수를 사용자에게서 입력 받음
	StringBuffer hiddenWord; // 숨긴 글자를 가진 단어
	String newWord; // 게임을 위해 선정된 단어
	Scanner scanner; // 키 입력
	int failCount; // 틀린 횟수

	public hangmanGUI() {
		scanner = new Scanner(System.in);
	}

	// 게임을 시작하는 메소드
	public void run() {
		System.out.println("지금부터 행맨 개임을 시작합니다.");
		System.out.print("게임 난이도를 입력하세요(1-5)>>");
		HIDDENCHAR = scanner.nextInt();
		if (HIDDENCHAR < 1 || HIDDENCHAR > 5) {
			System.out.println("지원하지 않는 게임 난이도입니다.");
			return;
		}
		Words words = new Words("C:\\Users\\Use\\Desktop\\Words.txt"); // 단어 선택하는 객체 생성
		while (true) {
			while (true) {
				newWord = words.getRandomWord(); // 랜덤한 단어 선택
				if (newWord.length() <= HIDDENCHAR) // 단어 길이가 난이도보다 작은 경우 다른 단어 선택
					continue;
				else
					break;
			}
			// if(newWord == null) break; // 단어 선택에 문제가 있는 경우 프로그램 종료
			makeHidden(); // 글자를 숨긴 단어 만들기
			go(); // 게임 진행
			System.out.print("Next(y/n)?");
			String answer = scanner.next();
			if (answer.equals("n")) // n을 입력하면 종료
				break;
		}
	}

	// 선택된 단어 newWord에 난이도에 맞는 개수의 글자를 숨긴 단어 hiddenWord를 만든다.
	void makeHidden() {
		hiddenWord = new StringBuffer(newWord);
		Random r = new Random();

		for (int k = 0; k < HIDDENCHAR; k++) {
			int index = r.nextInt(newWord.length());
			char c = newWord.charAt(index);
			for (int i = 0; i < newWord.length(); i++) {
				if (hiddenWord.charAt(i) == c)
					hiddenWord.setCharAt(i, '-');
			}
		}
	}

	// 사용자 키를 입력받으면서 행맨 게임을 진행한다. 5 번 틀리면 종료한다.
	// 한 단어 진행 후 y/n 물음에 대해 n를 입력하면 종료한다.
	void go() {
		failCount = 0;
		char key;
		do {
			if (failCount == 5) {
				System.out.println("5번 실패 하였습니다.");
				break;
			}
			System.out.println(hiddenWord);
			System.out.print(">>");
			String text = scanner.next();
			key = text.charAt(0);
		} while (!complete(key));
		System.out.println(newWord);
	}

	// 사용자가 입력한 문자 key가 숨긴 글자와 일치하는지 검사하고 일치하면 true를 리턴한다.
	// 그리고 나서 hiddenWord의 '-'문자를 key 문자로 변경한다.
	boolean complete(char key) {
		boolean hit = false;
		for (int i = 0; i < newWord.length(); i++) {
			if (hiddenWord.charAt(i) == '-' && newWord.charAt(i) == key) {
				hiddenWord.setCharAt(i, key);
				hit = true;
			}
		}
		if (!hit)
			failCount++;
		for (int i = 0; i < newWord.length(); i++) {
			if (hiddenWord.charAt(i) == '-')
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		hangmanGUI app = new hangmanGUI();
		app.run();
	}

}

// words.txt 파일을 읽고 파일에서 랜덤하게 단어를 추출하는 클래스
class Words {
	final int WORDMAX = 25143; // words.txt파일에 들어 있는 총 단어의 개수
	private String fileName; // 단어 파일 이름. 현재는 words.txt
	private Random r = new Random(); // 난수 발생기

	public Words(String fileName) {
		this.fileName = fileName;
	}

	public String getRandomWord() {
		// 파일을 읽기 위한 BufferedReader 객체를 생성한다.
		BufferedReader in = null;
		try {
			// 파일을 열고 파일을 읽기 위한 BufferedReader 객체 생성
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("file not found error");
			System.exit(0);
		} 
		int n = r.nextInt(WORDMAX); // 랜덤한 라인 번호 생성. n 번째 단어를 게임에 사용
		return readWord(in, n); // in 파일에서 n 번째 라인의 단어를 읽어서 리턴
	}

	// in 파일에서 n 번째 라인의 단어를 읽어 리턴하는 메소드
	private String readWord(BufferedReader in, int n) {
		String line = null; // 한 라인을 저장할 문자열 객체. 한 라인에는 하나의 단어만 있음
		try {
			while (n > 0) {
				line = in.readLine(); // 파일에서 한 라인(한 단어)를 읽는다.
				if (line == null) // eof를 만나면 문제 발생. 루프 종료
					break;
				n--;
			}
		} catch (IOException e) {
			System.exit(0);
		}
		return line; // n 번째 라인의 단어 리턴
	}
}