package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Deck {
	final static int MAX_NUM = 52;	//카드의 총 장 수
	final static int MAX_CARD = 5;// 핸드에 들 수 있는 카드 장 수
	private ArrayList<Card> playerCards;//플레이어 카드의 문양과 숫자를 나누어 담을 Card타입의 List 객체 변수 
	private ArrayList<Card> comCards;//컴퓨터 카드의 문양과 숫자를 나누어 담을 Card타입의 List 객체 변수 
	private int[] playerCard = new int[MAX_CARD];//유저가 받은 5장의 카드를 저장할 배열
	private int[] comCard = new int[MAX_CARD];//컴퓨터가 받은 5장의 카드를 저장할 배열

	public Deck() {//생성자에서 플레이어와 컴퓨터에게 카드를 5장씩 나누어 주는 init()메소드 호출
		init();
	}
	
	//플레이어와 컴퓨터에게 카드를 5장씩 나누어 주는 메소드
	private void init() {
		/////////////
		for (int i = 0; i < playerCard.length; i++) {
			playerCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 1~52 중 랜덤 값 반환
			for (int j = 0; j < i; j++) {
				if (playerCard[i] == playerCard[j]) {
					i--;
					break;
				} // 플레이어 카드 5장의 중복 값 제거 알고리즘
			}
		}
		Arrays.sort(playerCard);//오름차순으로 정렬
		/////////////플레이어에게 중복되지않은 카드 5장 선언
		
		/////////////
		for (int i = 0; i < comCard.length; i++) {
			comCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 1~53 중 랜덤 값 반환
			label: for (int j = 0; j < i; j++) {
				if (comCard[i] == comCard[j]) {
					i--;
					break;
				} // 컴퓨터 카드 5장의 중복 값 제거 알고리즘
				for (int k = 0; k < playerCard.length; k++) {
					if (comCard[i] == playerCard[k]) {
						i--;
						break label;
					} // // 컴퓨터와 플레이어 카드를 비교해서 중복 값 제거 알고리즘
				}
			}
		}
		Arrays.sort(comCard);	
		///////////////컴퓨터에게 중복되지않은 카드 5장 선언
	}
	
	//컴퓨터와 플레이어에게 할당된 카드의 int값을 이용해 
	//문양과 숫자를 나누고 게임을 진행하는 메소드
	public void run() {
		////////////////////
		playerCards = new ArrayList<Card>();	//ArrayList객체 생성
		for (int i = 0; i < playerCard.length; i++) {
			if(playerCard[i]<14) {
				playerCards.add(new Card(0, playerCard[i]-1));	// int값이 1~13까지라면, 문양은 스페이드 지정
			}else if(playerCard[i]>13  && playerCard[i]<27) {			
				playerCards.add(new Card(1, (playerCard[i]-14)));	// int값이 14~26까지라면, 문양은 클로버 지정
			}else if(playerCard[i]>26  && playerCard[i]<40) {
				playerCards.add(new Card(2, (playerCard[i]-27)));	// int값이 27~39까지라면, 문양은 하트 지정
			}else {
				playerCards.add(new Card(3, (playerCard[i]-40)));	// int값이 40~52까지라면, 문양은 다이아몬드 지정
			}
		}
		////////////////////플레이어카드 리스트에 담기 완료
		
		////////////////////
		comCards = new ArrayList<Card>();
		for (int i = 0; i < comCard.length; i++) {
			if(comCard[i]<14) {
				comCards.add(new Card(0, comCard[i]-1));	// int값이 1~13까지라면, 문양은 스페이드 지정
			}else if(comCard[i]>13  && comCard[i]<27) {
				comCards.add(new Card(1, (comCard[i]-14)));		// int값이 14~26까지라면, 문양은 클로버 지정
			}else if(comCard[i]>26  && comCard[i]<40) {
				comCards.add(new Card(2, (comCard[i]-27)));		// int값이 27~39까지라면, 문양은 하트 지정
			}else {
				comCards.add(new Card(3, (comCard[i]-40)));		// int값이 40~52까지라면, 문양은 다이아몬드 지정
			}
		}
		///////////////////컴퓨터카드 리스트에 담기 완료
		
		print(playerCards,comCards);	//콘솔창에 프린트하는 메소드 호출

		Calculation cal = new Calculation(playerCards, comCards);		//계산을 진행하는 Calculation클래스 객체를 생성
		cal.calculation();
	}
	
	public void print(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		////////////////////
		Iterator<Card> it = playerCards.iterator();	//ArrayList를 반복해서 출력하기 위한 Iterator 객체 생성
		System.out.print("플레이어 카드 : ");
		while(it.hasNext()){
		    System.out.print(it.next().toString());          
		}
		System.out.println();
		////////////////////플레이어 카드 콘솔창에 출력 완료
		
		////////////////////
		System.out.print("컴퓨터 카드 : ");
		Iterator<Card> it2 = comCards.iterator();//ArrayList를 반복해서 출력하기 위한 Iterator 객체 생성
		while(it2.hasNext()){
		    System.out.print(it2.next().toString());          
		}
		System.out.println();
		////////////////////컴퓨터 카드 콘솔창에 출력 완료
	}
}
