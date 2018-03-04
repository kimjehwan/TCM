package sanghee;


/*
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class hangmanGUI extends JFrame {
	hangmanGUI() {
		// Window ������ ���� [X]��ư�� ������ ���α׷��� ����ǰ� �Ѵ�.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("����");
		// Windows�� ũ�⸦ �����Ѵ�. ����500 X ����500 �ȼ�ũ��� �����ߴ�.
		/////////////
		// ���̾ƿ��� FlowLayout���� ����
		this.setLayout(new FlowLayout());
		// �̹��������� 1���� �غ��Ѵ� �̴� 15��� 19�࿡�� ����Ѵ�.
		ImageIcon img1 = new ImageIcon("image/hangman_img05.png");
		// ���̺� ���� �Ǵ� �̹����� ǥ���ǵ��� �����Ѵ�.
		JLabel lbl1 = new JLabel(img1);
		// ������ lbl1�� this(��ü ������)�� �����Ѵ�.
		this.add(lbl1);

		setSize(600, 600);
		// Windows�� ȭ�鿡 ���̰� �Ѵ�.
		setVisible(true);
	}

	// ����()�޼ҵ忡�� MyGUI �����ڰ� ����ǹǷ� Windowsâ�� ��µȴ�
	public static void main(String[] args) {
		new hangmanGUI();
	}
}

// main()�޼ҵ忡�� MyGUI Ŭ������ �����Ѵ�.
// �ᱹ ���� MyGUI()�����ڰ� ����ǹǷ� Windowâ�� ��µȴ�.
// ���θ޼ҵ� ����


 * import javax.swing.JFrame;
 * 
 * public class hangmanGUI { static class MyGUI extends JFrame{ MyGUI(){
 * //Window ������ ���� [X]��ư�� ������ ���α׷��� ����ǰ� �Ѵ�.
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setTitle("��ǰ���");
 * ///////////////////////////////////////////
 * 
 * 
 * /////////////////////////////////////////// //Windows�� ũ�⸦ �����Ѵ�. ����500 X
 * ����500 �ȼ�ũ��� �����ߴ�. setSize(600,600); //Windows�� ȭ�鿡 ���̰� �Ѵ�. setVisible(true);
 * } } }
 
*/

import java.util.*;
import java.io.*;

public class hangmanGUI {
	int HIDDENCHAR; // ����� ���� ������ ����ڿ��Լ� �Է� ����
	StringBuffer hiddenWord; // ���� ���ڸ� ���� �ܾ�
	String newWord; // ������ ���� ������ �ܾ�
	Scanner scanner; // Ű �Է�
	int failCount; // Ʋ�� Ƚ��

	public hangmanGUI() {
		scanner = new Scanner(System.in);
	}

	// ������ �����ϴ� �޼ҵ�
	public void run() {
		System.out.println("���ݺ��� ��� ������ �����մϴ�.");
		System.out.print("���� ���̵��� �Է��ϼ���(1-5)>>");
		HIDDENCHAR = scanner.nextInt();
		if (HIDDENCHAR < 1 || HIDDENCHAR > 5) {
			System.out.println("�������� �ʴ� ���� ���̵��Դϴ�.");
			return;
		}
		Words words = new Words("C:\\Users\\Use\\Desktop\\Words.txt"); // �ܾ� �����ϴ� ��ü ����
		while (true) {
			while (true) {
				newWord = words.getRandomWord(); // ������ �ܾ� ����
				if (newWord.length() <= HIDDENCHAR) // �ܾ� ���̰� ���̵����� ���� ��� �ٸ� �ܾ� ����
					continue;
				else
					break;
			}
			// if(newWord == null) break; // �ܾ� ���ÿ� ������ �ִ� ��� ���α׷� ����
			makeHidden(); // ���ڸ� ���� �ܾ� �����
			go(); // ���� ����
			System.out.print("Next(y/n)?");
			String answer = scanner.next();
			if (answer.equals("n")) // n�� �Է��ϸ� ����
				break;
		}
	}

	// ���õ� �ܾ� newWord�� ���̵��� �´� ������ ���ڸ� ���� �ܾ� hiddenWord�� �����.
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

	// ����� Ű�� �Է¹����鼭 ��� ������ �����Ѵ�. 5 �� Ʋ���� �����Ѵ�.
	// �� �ܾ� ���� �� y/n ������ ���� n�� �Է��ϸ� �����Ѵ�.
	void go() {
		failCount = 0;
		char key;
		do {
			if (failCount == 5) {
				System.out.println("5�� ���� �Ͽ����ϴ�.");
				break;
			}
			System.out.println(hiddenWord);
			System.out.print(">>");
			String text = scanner.next();
			key = text.charAt(0);
		} while (!complete(key));
		System.out.println(newWord);
	}

	// ����ڰ� �Է��� ���� key�� ���� ���ڿ� ��ġ�ϴ��� �˻��ϰ� ��ġ�ϸ� true�� �����Ѵ�.
	// �׸��� ���� hiddenWord�� '-'���ڸ� key ���ڷ� �����Ѵ�.
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

// words.txt ������ �а� ���Ͽ��� �����ϰ� �ܾ �����ϴ� Ŭ����
class Words {
	final int WORDMAX = 25143; // words.txt���Ͽ� ��� �ִ� �� �ܾ��� ����
	private String fileName; // �ܾ� ���� �̸�. ����� words.txt
	private Random r = new Random(); // ���� �߻���

	public Words(String fileName) {
		this.fileName = fileName;
	}

	public String getRandomWord() {
		// ������ �б� ���� BufferedReader ��ü�� �����Ѵ�.
		BufferedReader in = null;
		try {
			// ������ ���� ������ �б� ���� BufferedReader ��ü ����
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("file not found error");
			System.exit(0);
		} 
		int n = r.nextInt(WORDMAX); // ������ ���� ��ȣ ����. n ��° �ܾ ���ӿ� ���
		return readWord(in, n); // in ���Ͽ��� n ��° ������ �ܾ �о ����
	}

	// in ���Ͽ��� n ��° ������ �ܾ �о� �����ϴ� �޼ҵ�
	private String readWord(BufferedReader in, int n) {
		String line = null; // �� ������ ������ ���ڿ� ��ü. �� ���ο��� �ϳ��� �ܾ ����
		try {
			while (n > 0) {
				line = in.readLine(); // ���Ͽ��� �� ����(�� �ܾ�)�� �д´�.
				if (line == null) // eof�� ������ ���� �߻�. ���� ����
					break;
				n--;
			}
		} catch (IOException e) {
			System.exit(0);
		}
		return line; // n ��° ������ �ܾ� ����
	}
}