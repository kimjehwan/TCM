package sanghee.hangman;

import java.util.Scanner;

public class Hangman {
	static String hiddenString; // ������ ��� ���ڿ� ���� 
	static StringBuffer outputString;// ������ ���ܵ� ���ڿ�����
	static StringBuffer inputString; // ������Է��� ���ڸ� ������Ų �������
	static int remaninder; // ���� ���ڼ� 
	static int failed; // ������ Ƚ��

	public Hangman() {
		hiddenString = "hello";// ������ ������ "hello"
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int playGame() {
	
		outputString = new StringBuffer();

		for (int i = 0; i < hiddenString.length(); i++) { // hiddenString�� ���� ����ŭ '-'���
			outputString.append('_');
		}

		inputString = new StringBuffer();
//remaninder 5��  �ʱ�ȭ��Ų�� 
		remaninder = hiddenString.length(); // hiddenString�� ���ڼ��� ���㹮���� ���ڼ�
		failed = 0;

		System.out.println("\n�ܾ�(" + hiddenString.length() + "����" + "): " + outputString);
		drawMan(); // ������ �׸���
		new hangmanGUI();
/*		do {
			checkChar(readChar()); // �� ���ڸ� �Է¹޾Ƽ� �������� Ȯ��
			System.out.println("\n�ܾ�(" + hiddenString.length() + "����" + "): " + outputString);
			drawMan(); // �Է¹��ڿ� ���� ������ ���

		} while ((remaninder > 0) && (failed < 6)); // ������ ������ ���߰ų� 6���̻� Ʋ�������� �ݺ�
*/
		
		return failed;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////playgame
	//�������� Ȯ���Ѵ� �Լ� 
	public static void checkChar(char guess) {
		boolean already = false;//����ġ=ON
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == guess) { // �̹��Է��ߴ� �������� ����
				System.out.println("\n �̹� �Էµ� �����Դϴ� �ٽ� �Է����ּ���,");
				already = true;//����ġ = OFF
				
			}
		}

		if (!already) { //����ġ�� On���� OFF���� �Ǻ�
			// �Է��� ���ڵ��� ���ӿ� �߰�
			//�Էµ� ���ڵ��� ������ ��������� ����ڰ� ���� �Է��� �ѹ��ڸ� �߰������ش�
			inputString.append(guess);
          
			boolean success = false;//�Ʒ��� if�� ������ �Ǻ��ϱ� ���� boolean ���� 
			for (int i = 0; i < hiddenString.length(); i++) {
				if (hiddenString.charAt(i) == guess) { // ������ �ش繮�ڰ� �ִ��� ����
					System.out.println("�� ������ϴ�! ��");
					outputString.setCharAt(i, guess); // ������ ���ڰ� ������ -�� ���ڷ� ����
					remaninder--; // ���� ���ڼ� 1 ����
					success = true; // �Է��� ���ڰ� ������ �־����� ǥ��
				}
			}
			if (!success) {
				System.out.println("�� Ʋ�Ƚ��ϴ�! ��");
				failed++; // �Է��� ���ڰ� ������ ������ ����Ƚ���� 1����
			}
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////checkChar////////
	public void drawMan() {
		System.out.println("����������");

		switch (failed) { // ����Ƚ���� ���� �����뿡 ����� �׸�
		case 0:
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			break;
		case 1:
			System.out.println("��        ��");// 1�� ������ ��� �Ӹ� �׸�
			System.out.println("��        ��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			break;
		case 2:
			System.out.println("��        ��");// 2�� ������ ��� �Ӹ� �׸�
			System.out.println("��     ����");
			System.out.println("��     ��   ");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			break;
		case 3:
			System.out.println("��        ��");// 3�� ������ ��� �Ӹ� �׸�
			System.out.println("��     ������");
			System.out.println("��     ������ ");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			break;
		case 4:
			System.out.println("��        ��");// 4�� ������ ��� �Ӹ� �׸�
			System.out.println("��     ������");
			System.out.println("��     ������ ");
			System.out.println("��     ����");
			System.out.println("��     ��");
			System.out.println("��     ��");
			System.out.println("��");
			break;
		case 5:
			System.out.println("��        ��");// 5�� ������ ��� �Ӹ� �׸�
			System.out.println("��     ������");
			System.out.println("��     ������ ");
			System.out.println("��     ������");
			System.out.println("��     ��   ��");
			System.out.println("��     ��   ��");
			System.out.println("��");
			break;
		case 6:
			System.out.println("��        ��");// 6�� ������ ��� �Ӹ� �׸�
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			System.out.println("��");
			break;
		}
	}
//////////////////////////////////////////////////////////////////////////////drawMan/////////////////////////////////////////////////
	public char readChar() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String user; //����ڰ� �Է��� ���ڿ��� ��� ����  

		System.out.print("���ڸ� �Է��ϼ���(���� ��ȸ " + (6 - failed) + "):");
		//����ڰ� �Է��� ���ڿ��� user ������ ��´� 
		user = sc.nextLine(); // Ű����κ��� ������ �Է�
		return user.charAt(0); // �Է¹��� ���ڿ� �� ù��° ���ڸ� ��ȯ
	}

}
