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

			// ī�� ����
			shuffle(card);

			// ī�� �й�
			// ���� ī�带 ������ ��ǻ�Ͱ� 2�徿 ���� ������.
			getCard(userCard, comCard, card);

			// ���� ī��, ��ǻ�� ī�� ���
			System.out.println("������ ��ǻ�Ͱ� ���� ī��");
			System.out.println("player card: " + userCard[0] + "��, " + userCard[1] + "��");
			System.out.println("computer card: " + comCard[0] + "��, " + comCard[1] + "��");

			// ������ ��ǻ���� ������ �����´�.
			// �嶯 = 30, ���� = 29,..., �˸� = 20, ... ���
			int userScore = getScore(userCard);
			int comScore = getScore(comCard);

			// ������ ������ ���ؼ� ���и� ������.
			result = sorceCheck(userScore, comScore);

			Project_suttda_GuI psg = new Project_suttda_GuI();
			psg.GuI(userCard, comCard);

	}

	// ������ ��ǻ���� ������ �޾� ���п��θ� result������ �����Ѵ�.
	static int sorceCheck(int player, int com) {
		if (player > com) {
			System.out.println("�¸��߽��ϴ�.");
			return 1;
		} else if (player == com) {
			System.out.println("�����ϴ�.");
			return 2;
		} else {
			System.out.println("�й��߽��ϴ�.");
			return 3;
		}
	}

	// �÷��̾��� ī�忡 ���� ������ �����Ѵ�.
	static int getScore(int[] playerCard) {
		int player = 0;
		// score check
		if (playerCard[0] == 3) {
			if (playerCard[1] == 8)
				;
			player = 24; // 3,8 ����
		} else if (playerCard[0] == 1) {
			if (playerCard[1] == 8)
				;
			{
				player = 23; // 1,8����
			}
			if (playerCard[1] == 3) {
				player = 22; // 1,3����
			}
		} else if ((playerCard[0]) == (playerCard[1] % 10)) {
			player = 21; // ��
		} else if (playerCard[0] == 1 || playerCard[0] == 11) {
			if (playerCard[1] == 2 || playerCard[1] == 12) {
				player = 20; // �˸� (1��, 2��)
			} else if (playerCard[1] == 4 || playerCard[1] == 14) {
				player = 19; // ���� (1��, 4��)
			} else if (playerCard[1] == 9 || playerCard[1] == 19) {
				player = 18; // ���� (1��, 9��)
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 17; // ��� (1��, 10��)
			}
		} else if (playerCard[0] == 4 || playerCard[0] == 14) {
			if (playerCard[1] == 6 || playerCard[1] == 16) {
				player = 16; // ���� (4��, 6��)
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 15; // ��� (4��, 10��)
			}
		} else {
			player = (playerCard[0] + playerCard[1]) % 10;
		}
		return player;
	}

	// ������ ��ǻ�Ϳ��� ī�带 �й��Ѵ�.
	static void getCard(int[] userCard, int[] comCard, int[] card) {
		int temp;
		// card�迭�� �̹� shuffle�Ǿ� �ֱ� ������ ������� �־
		// ������ ī�带 �޴´�.
		userCard[0] = card[0];
		comCard[0] = card[1];
		userCard[1] = card[2];
		comCard[1] = card[3];

		// ī�� ����
		// ���� ���ڰ� �տ� ���� swap
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

	// ī�� ����
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
