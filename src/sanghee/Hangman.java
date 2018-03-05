package sanghee;

import java.util.Scanner;

public class Hangman {
	String hiddenString; // ������ ���ڿ�(����)
	StringBuffer outputString;// �÷��̾��� �Է¿� ���� ����� ������ ���ڿ�
	StringBuffer inputString; // �÷��̾ �Է��� ���ڵ��� ����
	int remaninder; // ���� ���ڿ� (�� ���߰� �����ִ� ������ ��)
	int failed; // ������ Ƚ��

	public Hangman() {
		hiddenString = "hello";// ������ "hello"
	}

	public int playGame() {
		outputString = new StringBuffer();

		for (int i = 0; i < hiddenString.length(); i++) { // hiddenString�� ���� ����ŭ '-'���
			outputString.append('_');
		}

		inputString = new StringBuffer();

		remaninder = hiddenString.length(); // hiddenString�� ���ڼ��� ���㹮���� ���ڼ�
		failed = 0;

		System.out.println("\n�ܾ�(" + hiddenString.length() + "����" + "): " + outputString);
		drawMan(); // ������ �׸���

		do {
			checkChar(readChar()); // �� ���ڸ� �Է¹޾Ƽ� �������� Ȯ��
			System.out.println("\n�ܾ�(" + hiddenString.length() + "����" + "): " + outputString);
			drawMan(); // �Է¹��ڿ� ���� ������ ���
		} while ((remaninder > 0) && (failed < 6)); // ������ ������ ���߰ų� 6���̻� Ʋ�������� �ݺ�

		return failed;
	}

	public void checkChar(char guess) {
		boolean already = false;
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == guess) { // �̹��Է��ߴ� �������� ����
				System.out.println("\n �̹� �Էµ� �����Դϴ� �ٽ� �Է����ּ���,");
				already = true;
			}
		}

		if (!already) {
			// �Է��� ���ڵ��� ���ӿ� �߰�
			inputString.append(guess);

			boolean success = false;
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

	public char readChar() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String user;

		System.out.print("���ڸ� �Է��ϼ���(���� ��ȸ " + (6 - failed) + "):");
		user = sc.nextLine(); // Ű����κ��� ������ �Է�
		return user.charAt(0); // �Է¹��� ���ڿ� �� ù��° ���ڸ� ��ȯ
	}

}
