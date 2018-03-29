package sanghee.hangman;

import java.util.Scanner;

public class Hangman {
	static String hiddenString; // 정답을 담는 문자열 변수 
	static StringBuffer outputString;// 정답을 숨겨든 문자열모임
	static StringBuffer inputString; // 사용자입력한 문자를 누적시킨 저장공간
	static int remaninder; // 맞출 문자수 
	static int failed; // 실패한 횟수

	public Hangman() {
		hiddenString = "hello";// 생성자 문제는 "hello"
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int playGame() {
	
		outputString = new StringBuffer();

		for (int i = 0; i < hiddenString.length(); i++) { // hiddenString의 문자 수만큼 '-'출력
			outputString.append('_');
		}

		inputString = new StringBuffer();
//remaninder 5로  초기화시킨다 
		remaninder = hiddenString.length(); // hiddenString의 문자수가 맞춤문제의 문자수
		failed = 0;

		System.out.println("\n단어(" + hiddenString.length() + "글자" + "): " + outputString);
		drawMan(); // 교수대 그리기
		new hangmanGUI();
/*		do {
			checkChar(readChar()); // 한 문자를 입력받아서 정답인지 확인
			System.out.println("\n단어(" + hiddenString.length() + "글자" + "): " + outputString);
			drawMan(); // 입력문자에 따른 교수대 출력

		} while ((remaninder > 0) && (failed < 6)); // 문제를 완전히 맞추거나 6번이상 틀릴때까지 반복
*/
		
		return failed;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////playgame
	//정답인지 확인한는 함수 
	public static void checkChar(char guess) {
		boolean already = false;//스위치=ON
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == guess) { // 이미입력했던 문자인지 조사
				System.out.println("\n 이미 입력된 문자입니다 다시 입력해주세요,");
				already = true;//스위치 = OFF
				
			}
		}

		if (!already) { //스위치가 On인지 OFF인지 판별
			// 입력한 문자들의 모임에 추가
			//입력된 문자들의 누적된 저장공간에 사용자가 새로 입력한 한문자를 추가시켜준다
			inputString.append(guess);
          
			boolean success = false;//아래에 if문 진입을 판별하기 위한 boolean 변수 
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////checkChar////////
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
//////////////////////////////////////////////////////////////////////////////drawMan/////////////////////////////////////////////////
	public char readChar() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String user; //사용자가 입력한 문자열을 담는 변수  

		System.out.print("문자를 입력하세요(남은 기회 " + (6 - failed) + "):");
		//사용자가 입력한 문자열을 user 변수에 담는다 
		user = sc.nextLine(); // 키보드로부터 한줄을 입력
		return user.charAt(0); // 입력받은 문자열 중 첫번째 문자를 반환
	}

}
