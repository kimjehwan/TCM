package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Deck {
	final static int MAX_NUM = 52;	//ī���� �� �� ��
	final static int MAX_CARD = 5;// �ڵ忡 �� �� �ִ� ī�� �� ��
	private ArrayList<Card> playerCards;//�÷��̾� ī���� ����� ���ڸ� ������ ���� CardŸ���� List ��ü ���� 
	private ArrayList<Card> comCards;//��ǻ�� ī���� ����� ���ڸ� ������ ���� CardŸ���� List ��ü ���� 
	private int[] playerCard = new int[MAX_CARD];//������ ���� 5���� ī�带 ������ �迭
	private int[] comCard = new int[MAX_CARD];//��ǻ�Ͱ� ���� 5���� ī�带 ������ �迭

	public Deck() {//�����ڿ��� �÷��̾�� ��ǻ�Ϳ��� ī�带 5�徿 ������ �ִ� init()�޼ҵ� ȣ��
		init();
	}
	
	//�÷��̾�� ��ǻ�Ϳ��� ī�带 5�徿 ������ �ִ� �޼ҵ�
	private void init() {
		/////////////
		for (int i = 0; i < playerCard.length; i++) {
			playerCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 1~52 �� ���� �� ��ȯ
			for (int j = 0; j < i; j++) {
				if (playerCard[i] == playerCard[j]) {
					i--;
					break;
				} // �÷��̾� ī�� 5���� �ߺ� �� ���� �˰���
			}
		}
		Arrays.sort(playerCard);//������������ ����
		/////////////�÷��̾�� �ߺ��������� ī�� 5�� ����
		
		/////////////
		for (int i = 0; i < comCard.length; i++) {
			comCard[i] = (int) (Math.random() * MAX_NUM) + 1; // 1~53 �� ���� �� ��ȯ
			label: for (int j = 0; j < i; j++) {
				if (comCard[i] == comCard[j]) {
					i--;
					break;
				} // ��ǻ�� ī�� 5���� �ߺ� �� ���� �˰���
				for (int k = 0; k < playerCard.length; k++) {
					if (comCard[i] == playerCard[k]) {
						i--;
						break label;
					} // // ��ǻ�Ϳ� �÷��̾� ī�带 ���ؼ� �ߺ� �� ���� �˰���
				}
			}
		}
		Arrays.sort(comCard);	
		///////////////��ǻ�Ϳ��� �ߺ��������� ī�� 5�� ����
	}
	
	//��ǻ�Ϳ� �÷��̾�� �Ҵ�� ī���� int���� �̿��� 
	//����� ���ڸ� ������ ������ �����ϴ� �޼ҵ�
	public void run() {
		////////////////////
		playerCards = new ArrayList<Card>();	//ArrayList��ü ����
		for (int i = 0; i < playerCard.length; i++) {
			if(playerCard[i]<14) {
				playerCards.add(new Card(0, playerCard[i]-1));	// int���� 1~13�������, ������ �����̵� ����
			}else if(playerCard[i]>13  && playerCard[i]<27) {			
				playerCards.add(new Card(1, (playerCard[i]-14)));	// int���� 14~26�������, ������ Ŭ�ι� ����
			}else if(playerCard[i]>26  && playerCard[i]<40) {
				playerCards.add(new Card(2, (playerCard[i]-27)));	// int���� 27~39�������, ������ ��Ʈ ����
			}else {
				playerCards.add(new Card(3, (playerCard[i]-40)));	// int���� 40~52�������, ������ ���̾Ƹ�� ����
			}
		}
		////////////////////�÷��̾�ī�� ����Ʈ�� ��� �Ϸ�
		
		////////////////////
		comCards = new ArrayList<Card>();
		for (int i = 0; i < comCard.length; i++) {
			if(comCard[i]<14) {
				comCards.add(new Card(0, comCard[i]-1));	// int���� 1~13�������, ������ �����̵� ����
			}else if(comCard[i]>13  && comCard[i]<27) {
				comCards.add(new Card(1, (comCard[i]-14)));		// int���� 14~26�������, ������ Ŭ�ι� ����
			}else if(comCard[i]>26  && comCard[i]<40) {
				comCards.add(new Card(2, (comCard[i]-27)));		// int���� 27~39�������, ������ ��Ʈ ����
			}else {
				comCards.add(new Card(3, (comCard[i]-40)));		// int���� 40~52�������, ������ ���̾Ƹ�� ����
			}
		}
		///////////////////��ǻ��ī�� ����Ʈ�� ��� �Ϸ�
		
		print(playerCards,comCards);	//�ܼ�â�� ����Ʈ�ϴ� �޼ҵ� ȣ��

		Calculation cal = new Calculation(playerCards, comCards);		//����� �����ϴ� CalculationŬ���� ��ü�� ����
		cal.calculation();
	}
	
	public void print(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		////////////////////
		Iterator<Card> it = playerCards.iterator();	//ArrayList�� �ݺ��ؼ� ����ϱ� ���� Iterator ��ü ����
		System.out.print("�÷��̾� ī�� : ");
		while(it.hasNext()){
		    System.out.print(it.next().toString());          
		}
		System.out.println();
		////////////////////�÷��̾� ī�� �ܼ�â�� ��� �Ϸ�
		
		////////////////////
		System.out.print("��ǻ�� ī�� : ");
		Iterator<Card> it2 = comCards.iterator();//ArrayList�� �ݺ��ؼ� ����ϱ� ���� Iterator ��ü ����
		while(it2.hasNext()){
		    System.out.print(it2.next().toString());          
		}
		System.out.println();
		////////////////////��ǻ�� ī�� �ܼ�â�� ��� �Ϸ�
	}
}
