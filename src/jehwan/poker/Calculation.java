package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Calculation {

	private Rank[] ranks = new Rank[10];

	// �����ڿ��� ī���� ��ũ�� �Ǵ��Ѵ�.
	public Calculation(ArrayList<Card> playerCards, ArrayList<Card> comCards) {

		ranks[0] = isStraightFlush(playerCards);// ��Ƽ��, �齺��,���� �� ������ �ִ� �޼ҵ�
		ranks[1] = isStraightFlush(comCards);

		ranks[2] = isFourCard_Fullhouse(playerCards);// ��ī��, Ǯ�Ͽ콺�� ������ �ִ� �޼ҵ�
		ranks[3] = isFourCard_Fullhouse(comCards);

		ranks[4] = isFlush(playerCards); // �÷��ø� �������ִ� �޼ҵ�
		ranks[5] = isFlush(comCards);

		ranks[6] = isStraight(playerCards);// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� �������ִ� �޼ҵ�
		ranks[7] = isStraight(comCards);

		ranks[8] = isPair(playerCards);// Ʈ����, �� ���, �� ���, ����ī�� �� �������ִ� �޼ҵ�
		ranks[9] = isPair(comCards);
	}

	private Rank isFourCard_Fullhouse(ArrayList<Card> cardDeck) {

		// count�� check���� ���� Ǯ�Ͽ콺 �Ǵ� ��ī�� �� ����
		int i = 0, count = 0, check = 0;
		// sequence�� ���� Ǯ�Ͽ콺 �Ǵ� ��ī�带 �����ش�.
		int sequence = 0;
		// ��ī���� ��� ��ī���� ����, Ǯ�Ͽ콺�� ��� ���� 3���� ���ڸ� �����ϴ� ����
		int num = 0;
		// sequence�� 3�̶�� fourCard_check�� true�� �Ǿ� ��ī��, false��� Ǯ�Ͽ콺
		boolean fourCard_check = false;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();

		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;
		}
		Arrays.sort(Straight);

		int temp = Straight[0];

		for (int j = 1; j < Straight.length; j++) {
			if (temp == Straight[j]) {
				count++;
				sequence++;
				if (count == 2) {
					num = Straight[j];
				}
				if (sequence == 3) {
					fourCard_check = true;
				}
			} else {
				temp = Straight[j];
				check++;
				sequence = 0;
			}
		}
		if (count == 3 && check == 1 && fourCard_check == true) {
			System.out.println("4�� ¥�� ���� : " + num + ", ��ī��");
			return new Rank(2, num);
		} else if (count == 3 && check == 1 && fourCard_check == false) {
			System.out.println("3�� ¥�� ���� : " + num + ", Ǯ�Ͽ콺");
			return new Rank(1, num);
		} else {
			return new Rank(0, 0);
		}
	}

	private Rank isStraightFlush(ArrayList<Card> cardDeck) {

		int[] Cards = new int[5];
		int straight = 0, flush = 0, i = 0;

		Iterator<Card> it = cardDeck.iterator();

		while (it.hasNext()) {
			Cards[i] = it.next().getRank();
			i++;
		}
		Arrays.sort(Cards);

		Rank isFlush = isFlush(cardDeck);
		Rank isStraight = isStraight(cardDeck);

		if (isStraight != null) {
			straight = isStraight.getGrade();
		}

		if (isFlush != null) {
			flush = isFlush.getGrade();
		}

		if (straight == 3 && flush == 1) {
			System.out.println("���� : " + cardDeck.get(0).getSuit() + ", �ξ� ��Ʈ����Ʈ �÷���");
			return new Rank(3, (cardDeck.get(0).getSuit()) * -1);
		} else if (straight == 2 && flush == 1) {
			System.out.println("���� : " + cardDeck.get(0).getSuit() + ", �� ��Ʈ����Ʈ �÷���");
			return new Rank(2, (cardDeck.get(0).getSuit()) * -1);
		} else if (straight == 1 && flush == 1) {
			System.out.println("���ϳ������� : " + Cards[4] + "��Ʈ����Ʈ �÷���");
			return new Rank(1, Cards[4]);
		} else {
			return new Rank(0, 0);
		}
	}

	private Rank isPair(ArrayList<Card> cardDeck) {

		// count�� check���� ���� (Ʈ���� �Ǵ� �� ���), �� ���, ����ī�� �� ����
		int i = 0, count = 0, check = 0;
		// sequence�� ���� (Ʈ���� �Ǵ� �� ���) �� �����ش�.
		int sequence = 0;

		int sameCard1 = 0, sameCard2 = 0;
		// sequence�� 2��� continue_check�� true�� �Ǿ� Ʈ����, false��� �� ���
		boolean continue_check = false;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();

		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;
		}
		Arrays.sort(Straight);

		int temp = Straight[0];

		for (int j = 1; j < Straight.length; j++) {
			if (temp == Straight[j]) {
				count++;
				sequence++;
				if (count == 1)
					sameCard1 = Straight[j];
				if (count == 2)
					sameCard2 = Straight[j];
				if (sequence == 2)
					continue_check = true;

			} else {
				temp = Straight[j];
				check++;
				sequence = 0;
			}
		}
		if (count == 0 && check == 4) {
			System.out.println("���� ���� ���ڴ� : " + Straight[4] + ", ����ī��");
			return new Rank(1, Straight[4]);
		} else if (count == 1 && check == 3) {
			System.out.println("����� ���ڴ� : " + sameCard1 + ", �����");
			return new Rank(2, sameCard1);
		} else if (count == 2 && check == 2 && continue_check == false) {
			if (sameCard1 > sameCard2) {
				System.out.println("����� ���ڴ� : " + sameCard1 + ", �����");
				return new Rank(3, sameCard1);
			} else {
				System.out.println("����� ���ڴ� : " + sameCard2 + ", �����");
				return new Rank(3, sameCard2);
			}
		} else if (count == 2 && check == 2 && continue_check == true) {
			System.out.println("Ʈ���� ���ڴ� : " + sameCard2 + ", Ʈ����");
			return new Rank(4, sameCard2);
		} else {
			return new Rank(0, 0);
		}
	}

	private Rank isStraight(ArrayList<Card> cardDeck) {

		int i = 0;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();

		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;
		}
		Arrays.sort(Straight);

		// ����ƾ or �ξ� ��Ʈ����Ʈ �÷���
		if (Straight[0] == 0 && Straight[1] == 9 && Straight[2] == 10 && Straight[3] == 11 && Straight[4] == 12) {
			System.out.println("����ƾ");
			return new Rank(3, 0);
			// �齺Ʈ����Ʈ�÷��� or �齺Ʈ����Ʈ
		} else if (Straight[0] == 0 && Straight[1] == 1 && Straight[2] == 2 && Straight[3] == 3 && Straight[4] == 4) {
			System.out.println("�齺Ʈ����Ʈ");
			return new Rank(2, 0);
			// ��Ʈ����Ʈ�÷��� or ��Ʈ����Ʈ
		} else if (Straight[0] == (Straight[1] - 1) && Straight[0] == (Straight[2] - 2)
				&& Straight[0] == (Straight[3] - 3) && Straight[0] == (Straight[4] - 4)) {
			System.out.println("���� ���� ���� : " + Straight[4] + ", ��Ʈ����Ʈ");
			return new Rank(1, Straight[4]);
		} else {
			return new Rank(0, 0);
		}
	}

	public Rank isFlush(ArrayList<Card> cardDeck) {

		int[] Ranks = new int[5];
		int heart = 0, clover = 0, diamond = 0, spade = 0, i = 0, temp;
		Card temp_Card;
		Iterator<Card> it = cardDeck.iterator();
		while (it.hasNext()) {
			temp_Card = it.next();

			temp = temp_Card.getSuit();
			Ranks[i] = temp_Card.getRank();
			i++;

			if (temp == 0) {
				spade++;
			} else if (temp == 1) {
				clover++;
			} else if (temp == 2) {
				heart++;
			} else {
				diamond++;
			}
		}

		Arrays.sort(Ranks);

		if (spade == 5 || clover == 5 || heart == 5 || diamond == 5) {
			System.out.println("���� ���� ���ڴ� : " + Ranks[4] + ", �÷�����");
			return new Rank(1, Ranks[4]);
		} else {
			return new Rank(0, 0);
		}

	}

	public int calculation() {
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ranks[0].getGrade() > ranks[1].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			return 0;
		} else if (ranks[0].getGrade() < ranks[1].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			return 0;
		} else if ((ranks[0].getGrade() == ranks[1].getGrade()) && (ranks[0].getNumber() != ranks[1].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ƽ��,�齺��,���ø� ��

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ranks[2].getGrade() > ranks[3].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			return 0;
		} else if (ranks[2].getGrade() < ranks[3].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			return 0;
		} else if ((ranks[2].getGrade() == ranks[3].getGrade()) && (ranks[2].getNumber() != ranks[3].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��ī��,Ǯ�Ͽ콺 �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ranks[4].getGrade() > ranks[5].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			return 0;
		} else if (ranks[4].getGrade() < ranks[5].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			return 0;
		} else if ((ranks[4].getGrade() == ranks[5].getGrade()) && (ranks[4].getNumber() != ranks[5].getNumber())) {
			if (ranks[4].getNumber() > ranks[5].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// �÷��� �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ranks[6].getGrade() > ranks[7].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			return 0;
		} else if (ranks[6].getGrade() < ranks[7].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			return 0;
		} else if ((ranks[6].getGrade() == ranks[7].getGrade()) && (ranks[6].getNumber() != ranks[7].getNumber())) {
			if (ranks[6].getNumber() > ranks[7].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				return 0;
			} else if(ranks[6].getNumber() < ranks[7].getNumber()){
				System.out.println("��ǻ�� �¸�~!");
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ranks[8].getGrade() > ranks[9].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			return 0;
		} else if (ranks[8].getGrade() < ranks[9].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			return 0;
		} else if ((ranks[8].getGrade() == ranks[9].getGrade()) && (ranks[8].getNumber() != ranks[9].getNumber())) {
			if (ranks[8].getNumber() > ranks[9].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				return 0;
			} else if(ranks[8].getNumber() < ranks[9].getNumber()){
				System.out.println("��ǻ�� �¸�~!");
				return 0;
			} 
		}else {
			System.out.println("���º�!");
			return 0;
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� ��
		return 0;
	}
}
