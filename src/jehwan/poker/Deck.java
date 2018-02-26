package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Deck {
	final static int MAX_NUM = 52;
	final static int MAX_CARD = 5;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> comCards;
	private int[] playerCard = new int[MAX_CARD];
	private int[] comCard = new int[MAX_CARD];
	
	//private ArrayList<Card> testCards1= new ArrayList<Card>();
	//private ArrayList<Card> testCards2= new ArrayList<Card>(); //카드 테스트 용도

	public Deck() {
		init();
	}
	
	private void init() {
		for (int i = 0; i < playerCard.length; i++) {
			playerCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 랜덤 값 반환
			for (int j = 0; j < i; j++) {
				if (playerCard[i] == playerCard[j]) {
					i--;
					break;
				} // 중복 값 제거1
			}
		}
		Arrays.sort(playerCard);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		
		for (int i = 0; i < comCard.length; i++) {
			comCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 랜덤 값 반환
			label: for (int j = 0; j < i; j++) {
				if (comCard[i] == comCard[j]) {
					i--;
					break;
				} // 중복 값 제거1
				for (int k = 0; k < playerCard.length; k++) {
					if (comCard[i] == playerCard[k]) {
						i--;
						break label;
					} // 중복 값 제거2
				}
			}
		}
		Arrays.sort(comCard);	
	}
	
	public void run() {
		playerCards = new ArrayList<Card>();
		for (int i = 0; i < playerCard.length; i++) {
			if(playerCard[i]<14) {
				playerCards.add(new Card(0, playerCard[i]-1));
			}else if(playerCard[i]>13  && playerCard[i]<27) {			
				playerCards.add(new Card(1, (playerCard[i]-14)));
			}else if(playerCard[i]>26  && playerCard[i]<40) {
				playerCards.add(new Card(2, (playerCard[i]-27)));
			}else {
				playerCards.add(new Card(3, (playerCard[i]-40)));
			}
		}

		///////////////////////////////////////////////////////////////////////////////////
		comCards = new ArrayList<Card>();
		for (int i = 0; i < comCard.length; i++) {
			if(comCard[i]<14) {
				comCards.add(new Card(0, comCard[i]-1));
			}else if(comCard[i]>13  && comCard[i]<27) {
				comCards.add(new Card(1, (comCard[i]-14)));
			}else if(comCard[i]>26  && comCard[i]<40) {
				comCards.add(new Card(2, (comCard[i]-27)));
			}else {
				comCards.add(new Card(3, (comCard[i]-40)));
			}
		}
		
		print(playerCards,comCards);

		Calculation cal = new Calculation(playerCards, comCards);
		cal.calculation();
		
		/*testCards1.add(new Card(0,0));
		testCards1.add(new Card(0,9));
		testCards1.add(new Card(0,10));
		testCards1.add(new Card(0,11));
		testCards1.add(new Card(0,12));
		
		testCards2.add(new Card(1,0));
		testCards2.add(new Card(1,9));
		testCards2.add(new Card(1,10));
		testCards2.add(new Card(1,11));
		testCards2.add(new Card(1,12));

		print(testCards1,testCards2);
		Calculation test = new Calculation(testCards1, testCards2);
		test.calculation();*/  //카드 테스트 용도
	}
	
	public void print(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		Iterator<Card> it = playerCards.iterator();
		System.out.print("플레이어 카드 : ");
		while(it.hasNext()){
		    System.out.print(it.next().toString());          
		}
		System.out.println();
		
		System.out.print("컴퓨터 카드 : ");
		Iterator<Card> it2 = comCards.iterator();
		while(it2.hasNext()){
		    System.out.print(it2.next().toString());          
		}
		System.out.println();
	}
}
