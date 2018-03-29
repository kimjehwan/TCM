package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Calculation {

	private Rank[] ranks = new Rank[10];
	private ArrayList<Card> playerCards;
	private ArrayList<Card> comCards;
	private String winner;
	public Calculation(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		this.playerCards = playerCards;
		this.comCards = comCards;
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
			//count�� 3�̸鼭, check�� 1�̰�, ��ī�� üũ�� true��� ��ī���̴�.
			return new Rank(2, num,"��ī��, "+ num);
		} else if (count == 3 && check == 1 && fourCard_check == false) {
			//count�� 3�̸鼭, check�� 1�̰�, ��ī�� üũ�� false��� Ǯ�Ͽ콺�̴�.
			return new Rank(1, num,"Ǯ�Ͽ콺, "+ num);
		} else {
			return new Rank(0, 0, null);
		}
	}

	private Rank isStraightFlush(ArrayList<Card> cardDeck) {

		int[] Cards = new int[5];
		//��Ʈ����Ʈ���� �÷������� Ȯ�� �� ������� ��� ����
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
			//straight���� 3�̰�, flush���� 1�̶�� �ξ� ��Ʈ����Ʈ �÷����̴�.
			return new Rank(3, (cardDeck.get(0).getSuit()) * -1,"�ξ� ��Ʈ����Ʈ �÷���, " + Card.suitToString(cardDeck.get(0).getSuit()));
		} else if (straight == 2 && flush == 1) {
			//straight���� 2�̰�, flush���� 1�̶�� �� ��Ʈ����Ʈ �÷����̴�.
			return new Rank(2, (cardDeck.get(0).getSuit()) * -1,"�� ��Ʈ����Ʈ �÷���, " + Card.suitToString(cardDeck.get(0).getSuit()));
		} else if (straight == 1 && flush == 1) {
			//straight���� 1�̰�, flush���� 1�̶�� ��Ʈ����Ʈ �÷����̴�.
			return new Rank(1, Cards[4],"��Ʈ����Ʈ �÷���, " + Card.rankToString(Cards[4]));
		} else {
			return new Rank(0, 0, null);
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
			//count�� 0�̰�, check�� 4��� ����ī�� �̴�.
			return new Rank(1, Straight[4],"����ī��, " + Card.rankToString(Straight[4]));
		} else if (count == 1 && check == 3) {
			//count�� 1�̰�, check�� 3�̶�� ����� �̴�.
			return new Rank(2, sameCard1,"�����, " + Card.rankToString(sameCard1));
		} else if (count == 2 && check == 2 && continue_check == false) {
			//count�� 2�̰�, check�� 2��� ����� �̴�.
			////////////////////////////////////
			if (sameCard1 > sameCard2) {
				return new Rank(3, sameCard1,"�����, " +Card.rankToString(sameCard1));
			} else {
				return new Rank(3, sameCard2,"�����, " +Card.rankToString(sameCard2));
			}
			////////////////////////////////////����� �� ���� ���ڸ� ����
		} else if (count == 2 && check == 2 && continue_check == true) {
			//count�� 2�̰�, check�� 2�ε� ��Ƽ��üũ�� true��� Ʈ���� �̴�.
			return new Rank(4, sameCard2,"Ʈ����, " +Card.rankToString(sameCard2));
		} else {
			return new Rank(0, 0, null);
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
			return new Rank(3, 0,"����ƾ");
		// �齺Ʈ����Ʈ�÷��� or �齺Ʈ����Ʈ
		} else if (Straight[0] == 0 && Straight[1] == 1 && Straight[2] == 2 && Straight[3] == 3 && Straight[4] == 4) {
			return new Rank(2, 0,"�齺Ʈ����Ʈ");
		// ��Ʈ����Ʈ�÷��� or ��Ʈ����Ʈ
		} else if (Straight[0] == (Straight[1] - 1) && Straight[0] == (Straight[2] - 2)
				&& Straight[0] == (Straight[3] - 3) && Straight[0] == (Straight[4] - 4)) {
			return new Rank(1, Straight[4],"��Ʈ����Ʈ"+ Card.rankToString(Straight[4]));
		} else {
			return new Rank(0, 0, null);
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
			//4���� ���� �� 1���� 5���� ���ٸ� �÷���
			return new Rank(1, Ranks[4],"�÷���, "+ Card.rankToString(Ranks[4]));
		} else {
			return new Rank(0, 0,null);
		}

	}

	public int calculation() {		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[0] = isStraightFlush(playerCards);// ��Ƽ��, �齺��,���� �� ������ �ִ� �޼ҵ�
		ranks[1] = isStraightFlush(comCards);
		
		if (ranks[0].getGrade() > ranks[1].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[0].getResult());
			System.out.println("==============================================");
			winner="�÷��̾� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[0].getResult(),winner,1);
			return 0;
		} else if (ranks[0].getGrade() < ranks[1].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[1].getResult());
			System.out.println("==============================================");
			winner="��ǻ�� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[1].getResult(),winner,0);
			return 0;
		} else if ((ranks[0].getGrade() == ranks[1].getGrade()) && (ranks[0].getNumber() != ranks[1].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[0].getResult());
				System.out.println("==============================================");
				winner="�÷��̾� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[0].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[1].getResult());
				System.out.println("==============================================");
				winner="��ǻ�� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[1].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ƽ��,�齺��,���ø� ��

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[2] = isFourCard_Fullhouse(playerCards);// ��ī��, Ǯ�Ͽ콺�� ������ �ִ� �޼ҵ�
		ranks[3] = isFourCard_Fullhouse(comCards);
		
		if (ranks[2].getGrade() > ranks[3].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[2].getResult());
			System.out.println("==============================================");
			winner="�÷��̾� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[2].getResult(),winner,1);
			return 0;
		} else if (ranks[2].getGrade() < ranks[3].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[3].getResult());
			System.out.println("==============================================");
			winner="��ǻ�� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[3].getResult(),winner,0);
			return 0;
		} else if ((ranks[2].getGrade() == ranks[3].getGrade()) && (ranks[2].getNumber() != ranks[3].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[2].getResult());
				System.out.println("==============================================");
				winner="�÷��̾� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[2].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[3].getResult());
				System.out.println("==============================================");
				winner="��ǻ�� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[3].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��ī��,Ǯ�Ͽ콺 �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[4] = isFlush(playerCards); // �÷��ø� �������ִ� �޼ҵ�
		ranks[5] = isFlush(comCards);
		
		if (ranks[4].getGrade() > ranks[5].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[4].getResult());
			System.out.println("==============================================");
			winner="�÷��̾� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[4].getResult(),winner,1);
			return 0;
		} else if (ranks[4].getGrade() < ranks[5].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[5].getResult());
			System.out.println("==============================================");
			winner="��ǻ�� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[5].getResult(),winner,0);
			return 0;
		} else if ((ranks[4].getGrade() == ranks[5].getGrade()) && (ranks[4].getNumber() != ranks[5].getNumber())) {
			if (ranks[4].getNumber() > ranks[5].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[4].getResult());
				System.out.println("==============================================");
				winner="�÷��̾� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[4].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("��ǻ�� �¸�~!");
				winner="��ǻ�� �¸�~!";
				System.out.println("���� ī�� ���� : " + ranks[5].getResult());
				System.out.println("==============================================");
				new PokerGUI(playerCards, comCards,ranks[5].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// �÷��� �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[6] = isStraight(playerCards);// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� �������ִ� �޼ҵ�
		ranks[7] = isStraight(comCards);
		
		if (ranks[6].getGrade() > ranks[7].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[6].getResult());
			System.out.println("==============================================");
			winner="�÷��̾� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[6].getResult(),winner,1);
			return 0;
		} else if (ranks[6].getGrade() < ranks[7].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[7].getResult());
			System.out.println("==============================================");
			winner="��ǻ�� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[7].getResult(),winner,0);
			return 0;
		} else if ((ranks[6].getGrade() == ranks[7].getGrade()) && (ranks[6].getNumber() != ranks[7].getNumber())) {
			if (ranks[6].getNumber() > ranks[7].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[6].getResult());
				System.out.println("==============================================");
				winner="�÷��̾� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[6].getResult(),winner,1);
				return 0;
			} else if(ranks[6].getNumber() < ranks[7].getNumber()){
				System.out.println("��ǻ�� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[7].getResult());
				System.out.println("==============================================");
				winner="��ǻ�� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[7].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� ��
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[8] = isPair(playerCards);// Ʈ����, �� ���, �� ���, ����ī�� �� �������ִ� �޼ҵ�
		ranks[9] = isPair(comCards);		
		
		if (ranks[8].getGrade() > ranks[9].getGrade()) {
			System.out.println("�÷��̾� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[8].getResult());
			System.out.println("==============================================");
			winner="�÷��̾� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[8].getResult(),winner,1);
			return 0;
		} else if (ranks[8].getGrade() < ranks[9].getGrade()) {
			System.out.println("��ǻ�� �¸�~!");
			System.out.println("���� ī�� ���� : " + ranks[9].getResult());
			System.out.println("==============================================");
			winner="��ǻ�� �¸�~!";
			new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,0);
			return 0;
		} else if ((ranks[8].getGrade() == ranks[9].getGrade()) && (ranks[8].getNumber() != ranks[9].getNumber())) {
			if (ranks[8].getNumber() > ranks[9].getNumber()) {
				System.out.println("�÷��̾� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[8].getResult());
				System.out.println("==============================================");
				winner="�÷��̾� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[8].getResult(),winner,1);
				return 0;
			} else if(ranks[8].getNumber() < ranks[9].getNumber()){
				System.out.println("��ǻ�� �¸�~!");
				System.out.println("���� ī�� ���� : " + ranks[9].getResult());
				System.out.println("==============================================");
				winner="��ǻ�� �¸�~!";
				new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,0);
				return 0;
			} 
		}else {
			System.out.println("���º�!");
			System.out.println("���� ī�� ���� : " + ranks[9].getResult());
			System.out.println("==============================================");
			winner="���º�~!";
			new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,2);
			return 0;
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� ��
		return 0;
	}
	
}
