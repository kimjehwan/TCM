package jihong;

import java.util.Scanner;

public class abc {
	
	static int card[] = new int[20];
	static int userCard[] = new int[2];
	static int comCard[] = new int[2];
	static int win = 0;
	static int draw = 0;
	static int lose = 0;
	static String again;
	static int result;
	
	public abc() {
		Scanner scanner = new Scanner(System.in);

			// 카드 섞기
			shuffle(card);

			// 카드 분배
			// 섞인 카드를 유저와 컴퓨터가 2장씩 나눠 가진다.
			getCard(userCard, comCard, card);

			// 유저 카드, 컴퓨터 카드 출력
			System.out.println("유저와 컴퓨터가 뽑은 카드");
			System.out.println("player card: " + userCard[0] + "월, " + userCard[1] + "월");
			System.out.println("computer card: " + comCard[0] + "월, " + comCard[1] + "월");

			// 유저와 컴퓨터의 점수를 가져온다.
			// 장땡 = 30, 구땡 = 29,..., 알리 = 20, ... 등등
			int userScore = getScore(userCard);
			int comScore = getScore(comCard);

			// 가져온 점수를 비교해서 승패를 나눈다.
			result = sorceCheck(userScore, comScore);

			Project_suttda_GuI psg = new Project_suttda_GuI();
			psg.GuI(userCard, comCard);

	}

	// 유저와 컴퓨터의 점수를 받아 승패여부를 result값으로 리턴한다.
	static int sorceCheck(int player, int com) {
		if (player > com) {
			System.out.println("승리했습니다.");
			return 1;
		} else if (player == com) {
			System.out.println("비겼습니다.");
			return 2;
		} else {
			System.out.println("패배했습니다.");
			return 3;
		}
	}

	// 플레이어의 카드에 따라 점수를 리턴한다.
	static int getScore(int[] playerCard) {
		int player = 0;
		// score check
		if (playerCard[0] == 3) {
			if (playerCard[1] == 8)
				;
			player = 24; // 3,8 광땡
		} else if (playerCard[0] == 1) {
			if (playerCard[1] == 8)
				;
			{
				player = 23; // 1,8광땡
			}
			if (playerCard[1] == 3) {
				player = 22; // 1,3광땡
			}
		} else if ((playerCard[0]) == (playerCard[1] % 10)) {
			player = 21; // 땡
		} else if (playerCard[0] == 1 || playerCard[0] == 11) {
			if (playerCard[1] == 2 || playerCard[1] == 12) {
				player = 20; // 알리 (1월, 2월)
			} else if (playerCard[1] == 4 || playerCard[1] == 14) {
				player = 19; // 독사 (1월, 4월)
			} else if (playerCard[1] == 9 || playerCard[1] == 19) {
				player = 18; // 구삥 (1월, 9월)
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 17; // 장삥 (1월, 10월)
			}
		} else if (playerCard[0] == 4 || playerCard[0] == 14) {
			if (playerCard[1] == 6 || playerCard[1] == 16) {
				player = 16; // 세륙 (4월, 6월)
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 15; // 장사 (4월, 10월)
			}
		} else {
			player = (playerCard[0] + playerCard[1]) % 10;
		}
		return player;
	}

	// 유저와 컴퓨터에게 카드를 분배한다.
	static void getCard(int[] userCard, int[] comCard, int[] card) {
		int temp;
		// card배열은 이미 shuffle되어 있기 때문에 순서대로 주어도
		// 랜덤한 카드를 받는다.
		userCard[0] = card[0];
		comCard[0] = card[1];
		userCard[1] = card[2];
		comCard[1] = card[3];

		// 카드 정렬
		// 작은 숫자가 앞에 오게 swap
		if (userCard[0] > userCard[1]) {
			cardSwap(userCard);
		}
		if (comCard[0] > comCard[1]) {
			cardSwap(comCard);
		}
	}

	static void cardSwap(int[] playerCard) {
		int temp;

		temp = playerCard[0];
		playerCard[0] = playerCard[1];
		playerCard[1] = temp;
	}

	// 카드 섞기
	static void shuffle(int[] card) {
		boolean swit[] = new boolean[card.length];
		int w = 0;
		int r;

		// random
		while (w < card.length) {
			r = (int) (Math.random() * card.length);
			if (!swit[r]) {
				swit[r] = true;
				card[w] = (r) + 1; // 1~20
				w++;
				System.out.println();
			}
		}
	}
}
