package sanghee;

import java.util.Scanner;

public class Hangman {
	String hiddenString; // 숨겨진 문자열(문제)
	StringBuffer outputString;// 플레이어의 입력에 따른 결과로 보여줄 문자열
	StringBuffer inputString; // 플레이어가 입력한 문자들의 모임
	int remaninder; // 맞춤 문자열 (못 맞추고 남아있는 문자의 수)
	int failed; // 실패한 횟수

	public Hangman() {
		hiddenString = "hello";// 문제는 "hello"
	}

	public int playGame() {
		outputString = new StringBuffer();

		for (int i = 0; i < hiddenString.length(); i++) { // hiddenString의 문자 수만큼 '-'출력
			outputString.append('_');
		}

		inputString = new StringBuffer();

		remaninder = hiddenString.length(); // hiddenString의 문자수가 맞춤문제의 문자수
		failed = 0;

		System.out.println("\n단어(" + hiddenString.length() + "글자" + "): " + outputString);
		drawMan(); // 교수대 그리기

		do {
			checkChar(readChar()); // 한 문자를 입력받아서 정답인지 확인
			System.out.println("\n단어(" + hiddenString.length() + "글자" + "): " + outputString);
			drawMan(); // 입력문자에 따른 교수대 출력
		} while ((remaninder > 0) && (failed < 6)); // 문제를 완전히 맞추거나 6번이상 틀릴때까지 반복

		return failed;
	}

	public void checkChar(char guess) {
		boolean already = false;
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == guess) { // 이미입력했던 문자인지 조사
				System.out.println("\n 이미 입력된 문자입니다 다시 입력해주세요,");
				already = true;
			}
		}

		if (!already) {
			// 입력한 문자들의 모임에 추가
			inputString.append(guess);

			boolean success = false;
			for (int i = 0; i < hiddenString.length(); i++) {
				if (hiddenString.charAt(i) == guess) { // 문제애 해당문자가 있는지 조사
					System.out.println("▶ 맞췄습니다! ◀");
					outputString.setCharAt(i, guess); // 문제에 문자가 있으면 -를 문자로 변경
					remaninder--; // 맞출 문자수 1 감소
					success = true; // 입력한 문자가 문제에 있었음을 표시
				}
			}
			if (!success) {
				System.out.println("▷ 틀렸습니다! ◁");
				failed++; // 입력한 문자가 문제가 없으면 실패횟수를 1증가
			}
		}
	}

	public void drawMan() {
		System.out.println("┌───┐");

		switch (failed) { // 실패횟수에 따라 교수대에 사람을 그림
		case 0:
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("┴");
			break;
		case 1:
			System.out.println("│        Θ");// 1번 실패한 경우 머리 그림
			System.out.println("│        ┼");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("┴");
			break;
		case 2:
			System.out.println("│        Θ");// 2번 실패한 경우 머리 그림
			System.out.println("│     ┌┼");
			System.out.println("│     ┘   ");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("┴");
			break;
		case 3:
			System.out.println("│        Θ");// 3번 실패한 경우 머리 그림
			System.out.println("│     ┌┼┐");
			System.out.println("│     ┘│└ ");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("┴");
			break;
		case 4:
			System.out.println("│        Θ");// 4번 실패한 경우 머리 그림
			System.out.println("│     ┌┼┐");
			System.out.println("│     ┘│└ ");
			System.out.println("│     ┌┴");
			System.out.println("│     │");
			System.out.println("│     │");
			System.out.println("┴");
			break;
		case 5:
			System.out.println("│        Θ");// 5번 실패한 경우 머리 그림
			System.out.println("│     ┌┼┐");
			System.out.println("│     ┘│└ ");
			System.out.println("│     ┌┴┐");
			System.out.println("│     │   │");
			System.out.println("│     │   │");
			System.out.println("┴");
			break;
		case 6:
			System.out.println("│        Θ");// 6번 실패한 경우 머리 그림
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("│");
			System.out.println("┴");
			break;
		}
	}

	public char readChar() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String user;

		System.out.print("문자를 입력하세요(남은 기회 " + (6 - failed) + "):");
		user = sc.nextLine(); // 키보드로부터 한줄을 입력
		return user.charAt(0); // 입력받은 문자열 중 첫번째 문자를 반환
	}

}
