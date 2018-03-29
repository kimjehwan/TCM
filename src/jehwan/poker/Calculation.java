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

		// count와 check수에 따라 풀하우스 또는 포카드 를 구분
		int i = 0, count = 0, check = 0;
		// sequence에 따라 풀하우스 또는 포카드를 비교해준다.
		int sequence = 0;
		// 포카드일 경우 포카드의 숫자, 풀하우스일 경우 같은 3장의 숫자를 저장하는 변수
		int num = 0;
		// sequence가 3이라면 fourCard_check가 true가 되어 포카드, false라면 풀하우스
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
			//count가 3이면서, check가 1이고, 포카드 체크가 true라면 포카드이다.
			return new Rank(2, num,"포카드, "+ num);
		} else if (count == 3 && check == 1 && fourCard_check == false) {
			//count가 3이면서, check가 1이고, 포카드 체크가 false라면 풀하우스이다.
			return new Rank(1, num,"풀하우스, "+ num);
		} else {
			return new Rank(0, 0, null);
		}
	}

	private Rank isStraightFlush(ArrayList<Card> cardDeck) {

		int[] Cards = new int[5];
		//스트레이트인지 플러시인지 확인 한 결과값을 담는 변수
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
			//straight값이 3이고, flush값이 1이라면 로얄 스트레이트 플러시이다.
			return new Rank(3, (cardDeck.get(0).getSuit()) * -1,"로얄 스트레이트 플러시, " + Card.suitToString(cardDeck.get(0).getSuit()));
		} else if (straight == 2 && flush == 1) {
			//straight값이 2이고, flush값이 1이라면 백 스트레이트 플러시이다.
			return new Rank(2, (cardDeck.get(0).getSuit()) * -1,"백 스트레이트 플러시, " + Card.suitToString(cardDeck.get(0).getSuit()));
		} else if (straight == 1 && flush == 1) {
			//straight값이 1이고, flush값이 1이라면 스트레이트 플러시이다.
			return new Rank(1, Cards[4],"스트레이트 플러시, " + Card.rankToString(Cards[4]));
		} else {
			return new Rank(0, 0, null);
		}
	}

	private Rank isPair(ArrayList<Card> cardDeck) {

		// count와 check수에 따라 (트리플 또는 투 페어), 원 페어, 하이카드 를 구분
		int i = 0, count = 0, check = 0;
		// sequence에 따라 (트리플 또는 투 페어) 를 비교해준다.
		int sequence = 0;

		int sameCard1 = 0, sameCard2 = 0;
		// sequence가 2라면 continue_check가 true가 되어 트리플, false라면 투 페어
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
			//count가 0이고, check가 4라면 하이카드 이다.
			return new Rank(1, Straight[4],"하이카드, " + Card.rankToString(Straight[4]));
		} else if (count == 1 && check == 3) {
			//count가 1이고, check가 3이라면 원페어 이다.
			return new Rank(2, sameCard1,"원페어, " + Card.rankToString(sameCard1));
		} else if (count == 2 && check == 2 && continue_check == false) {
			//count가 2이고, check가 2라면 투페어 이다.
			////////////////////////////////////
			if (sameCard1 > sameCard2) {
				return new Rank(3, sameCard1,"투페어, " +Card.rankToString(sameCard1));
			} else {
				return new Rank(3, sameCard2,"투페어, " +Card.rankToString(sameCard2));
			}
			////////////////////////////////////투페어 중 높은 숫자를 저장
		} else if (count == 2 && check == 2 && continue_check == true) {
			//count가 2이고, check가 2인데 컨티뉴체크가 true라면 트리플 이다.
			return new Rank(4, sameCard2,"트리플, " +Card.rankToString(sameCard2));
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

		// 마운틴 or 로얄 스트레이트 플러스
		if (Straight[0] == 0 && Straight[1] == 9 && Straight[2] == 10 && Straight[3] == 11 && Straight[4] == 12) {
			return new Rank(3, 0,"마운틴");
		// 백스트레이트플러시 or 백스트레이트
		} else if (Straight[0] == 0 && Straight[1] == 1 && Straight[2] == 2 && Straight[3] == 3 && Straight[4] == 4) {
			return new Rank(2, 0,"백스트레이트");
		// 스트레이트플러시 or 스트레이트
		} else if (Straight[0] == (Straight[1] - 1) && Straight[0] == (Straight[2] - 2)
				&& Straight[0] == (Straight[3] - 3) && Straight[0] == (Straight[4] - 4)) {
			return new Rank(1, Straight[4],"스트레이트"+ Card.rankToString(Straight[4]));
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
			//4개의 문양 중 1개라도 5개가 같다면 플러시
			return new Rank(1, Ranks[4],"플러시, "+ Card.rankToString(Ranks[4]));
		} else {
			return new Rank(0, 0,null);
		}

	}

	public int calculation() {		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[0] = isStraightFlush(playerCards);// 로티플, 백스플,스플 을 구분해 주는 메소드
		ranks[1] = isStraightFlush(comCards);
		
		if (ranks[0].getGrade() > ranks[1].getGrade()) {
			System.out.println("플레이어 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[0].getResult());
			System.out.println("==============================================");
			winner="플레이어 승리~!";
			new PokerGUI(playerCards, comCards,ranks[0].getResult(),winner,1);
			return 0;
		} else if (ranks[0].getGrade() < ranks[1].getGrade()) {
			System.out.println("컴퓨터 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[1].getResult());
			System.out.println("==============================================");
			winner="컴퓨터 승리~!";
			new PokerGUI(playerCards, comCards,ranks[1].getResult(),winner,0);
			return 0;
		} else if ((ranks[0].getGrade() == ranks[1].getGrade()) && (ranks[0].getNumber() != ranks[1].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("플레이어 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[0].getResult());
				System.out.println("==============================================");
				winner="플레이어 승리~!";
				new PokerGUI(playerCards, comCards,ranks[0].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("컴퓨터 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[1].getResult());
				System.out.println("==============================================");
				winner="컴퓨터 승리~!";
				new PokerGUI(playerCards, comCards,ranks[1].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 로티플,백스플,스플만 비교

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[2] = isFourCard_Fullhouse(playerCards);// 포카드, 풀하우스를 구분해 주는 메소드
		ranks[3] = isFourCard_Fullhouse(comCards);
		
		if (ranks[2].getGrade() > ranks[3].getGrade()) {
			System.out.println("플레이어 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[2].getResult());
			System.out.println("==============================================");
			winner="플레이어 승리~!";
			new PokerGUI(playerCards, comCards,ranks[2].getResult(),winner,1);
			return 0;
		} else if (ranks[2].getGrade() < ranks[3].getGrade()) {
			System.out.println("컴퓨터 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[3].getResult());
			System.out.println("==============================================");
			winner="컴퓨터 승리~!";
			new PokerGUI(playerCards, comCards,ranks[3].getResult(),winner,0);
			return 0;
		} else if ((ranks[2].getGrade() == ranks[3].getGrade()) && (ranks[2].getNumber() != ranks[3].getNumber())) {
			if (ranks[0].getNumber() > ranks[1].getNumber()) {
				System.out.println("플레이어 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[2].getResult());
				System.out.println("==============================================");
				winner="플레이어 승리~!";
				new PokerGUI(playerCards, comCards,ranks[2].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("컴퓨터 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[3].getResult());
				System.out.println("==============================================");
				winner="컴퓨터 승리~!";
				new PokerGUI(playerCards, comCards,ranks[3].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 포카드,풀하우스 만 비교
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[4] = isFlush(playerCards); // 플러시를 구분해주는 메소드
		ranks[5] = isFlush(comCards);
		
		if (ranks[4].getGrade() > ranks[5].getGrade()) {
			System.out.println("플레이어 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[4].getResult());
			System.out.println("==============================================");
			winner="플레이어 승리~!";
			new PokerGUI(playerCards, comCards,ranks[4].getResult(),winner,1);
			return 0;
		} else if (ranks[4].getGrade() < ranks[5].getGrade()) {
			System.out.println("컴퓨터 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[5].getResult());
			System.out.println("==============================================");
			winner="컴퓨터 승리~!";
			new PokerGUI(playerCards, comCards,ranks[5].getResult(),winner,0);
			return 0;
		} else if ((ranks[4].getGrade() == ranks[5].getGrade()) && (ranks[4].getNumber() != ranks[5].getNumber())) {
			if (ranks[4].getNumber() > ranks[5].getNumber()) {
				System.out.println("플레이어 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[4].getResult());
				System.out.println("==============================================");
				winner="플레이어 승리~!";
				new PokerGUI(playerCards, comCards,ranks[4].getResult(),winner,1);
				return 0;
			} else {
				System.out.println("컴퓨터 승리~!");
				winner="컴퓨터 승리~!";
				System.out.println("승자 카드 족보 : " + ranks[5].getResult());
				System.out.println("==============================================");
				new PokerGUI(playerCards, comCards,ranks[5].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 플러시 만 비교
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[6] = isStraight(playerCards);// 스트레이트, 마운틴, 백스트레이트 를 구분해주는 메소드
		ranks[7] = isStraight(comCards);
		
		if (ranks[6].getGrade() > ranks[7].getGrade()) {
			System.out.println("플레이어 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[6].getResult());
			System.out.println("==============================================");
			winner="플레이어 승리~!";
			new PokerGUI(playerCards, comCards,ranks[6].getResult(),winner,1);
			return 0;
		} else if (ranks[6].getGrade() < ranks[7].getGrade()) {
			System.out.println("컴퓨터 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[7].getResult());
			System.out.println("==============================================");
			winner="컴퓨터 승리~!";
			new PokerGUI(playerCards, comCards,ranks[7].getResult(),winner,0);
			return 0;
		} else if ((ranks[6].getGrade() == ranks[7].getGrade()) && (ranks[6].getNumber() != ranks[7].getNumber())) {
			if (ranks[6].getNumber() > ranks[7].getNumber()) {
				System.out.println("플레이어 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[6].getResult());
				System.out.println("==============================================");
				winner="플레이어 승리~!";
				new PokerGUI(playerCards, comCards,ranks[6].getResult(),winner,1);
				return 0;
			} else if(ranks[6].getNumber() < ranks[7].getNumber()){
				System.out.println("컴퓨터 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[7].getResult());
				System.out.println("==============================================");
				winner="컴퓨터 승리~!";
				new PokerGUI(playerCards, comCards,ranks[7].getResult(),winner,0);
				return 0;
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 스트레이트, 마운틴, 백스트레이트 만 비교
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ranks[8] = isPair(playerCards);// 트리플, 투 페어, 원 페어, 하이카드 를 구분해주는 메소드
		ranks[9] = isPair(comCards);		
		
		if (ranks[8].getGrade() > ranks[9].getGrade()) {
			System.out.println("플레이어 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[8].getResult());
			System.out.println("==============================================");
			winner="플레이어 승리~!";
			new PokerGUI(playerCards, comCards,ranks[8].getResult(),winner,1);
			return 0;
		} else if (ranks[8].getGrade() < ranks[9].getGrade()) {
			System.out.println("컴퓨터 승리~!");
			System.out.println("승자 카드 족보 : " + ranks[9].getResult());
			System.out.println("==============================================");
			winner="컴퓨터 승리~!";
			new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,0);
			return 0;
		} else if ((ranks[8].getGrade() == ranks[9].getGrade()) && (ranks[8].getNumber() != ranks[9].getNumber())) {
			if (ranks[8].getNumber() > ranks[9].getNumber()) {
				System.out.println("플레이어 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[8].getResult());
				System.out.println("==============================================");
				winner="플레이어 승리~!";
				new PokerGUI(playerCards, comCards,ranks[8].getResult(),winner,1);
				return 0;
			} else if(ranks[8].getNumber() < ranks[9].getNumber()){
				System.out.println("컴퓨터 승리~!");
				System.out.println("승자 카드 족보 : " + ranks[9].getResult());
				System.out.println("==============================================");
				winner="컴퓨터 승리~!";
				new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,0);
				return 0;
			} 
		}else {
			System.out.println("무승부!");
			System.out.println("승자 카드 족보 : " + ranks[9].getResult());
			System.out.println("==============================================");
			winner="무승부~!";
			new PokerGUI(playerCards, comCards,ranks[9].getResult(),winner,2);
			return 0;
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 스트레이트, 마운틴, 백스트레이트 만 비교
		return 0;
	}
	
}
