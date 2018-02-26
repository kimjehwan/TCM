package jehwan.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Calculation {
	
	private Rank[] ranks = new Rank[10];

	//�����ڿ��� ī���� ��ũ�� �Ǵ��Ѵ�.
	public Calculation(ArrayList<Card> playerCards, ArrayList<Card> comCards) {
		
		ranks[0]=isStraightFlush(playerCards);// ��Ƽ��, �齺��,���� �� ������ �ִ� �޼ҵ�
		ranks[1]=isStraightFlush(comCards);

		ranks[2]=isFourCard_Fullhouse(playerCards);//  ��ī��, Ǯ�Ͽ콺�� ������ �ִ� �޼ҵ�
		ranks[3]=isFourCard_Fullhouse(comCards);
		
		ranks[4]=isFlush(playerCards); //�÷��ø� �������ִ� �޼ҵ�
		ranks[5]=isFlush(comCards);

		ranks[6]=isStraight(playerCards);// ��Ʈ����Ʈ, ����ƾ, �齺Ʈ����Ʈ �� �������ִ� �޼ҵ�
		ranks[7]=isStraight(comCards);
				
		ranks[8]=isPair(playerCards);//Ʈ����, �� ���, �� ���, ����ī�� �� �������ִ� �޼ҵ�
		ranks[9]=isPair(comCards);
		}

	
	private Rank isFourCard_Fullhouse(ArrayList<Card> cardDeck) {
		
		//count�� check���� ���� Ǯ�Ͽ콺 �Ǵ� ��ī�� �� ����
		int i=0, count=0,check=0;
		//sequence�� ���� Ǯ�Ͽ콺 �Ǵ� ��ī�带 �����ش�.
		int sequence=0;
		//sequence�� 3�̶�� fourCard_check�� true�� �Ǿ� ��ī��, false��� Ǯ�Ͽ콺
		boolean fourCard_check =false;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();
		
		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;			
		}
		Arrays.sort(Straight);	

		int temp = Straight[0];
		
		for(int j = 1 ; j < Straight.length ; j++) {
			if(temp==Straight[j]) {
				count++;
				sequence++;
				if(sequence==3) {
					fourCard_check=true;
				}
			}else {
				temp=Straight[j];
				check++;
				sequence=0;
			}
		}
		if(count==3 && check==1&& fourCard_check==true) {
			System.out.println("��ī��");
			return new Rank(1,0);
		}else if(count==3 && check==1&& fourCard_check==false){		
			System.out.println("Ǯ�Ͽ콺");	
			return new Rank(0,0);
		}else {
			return null;
		}
	}

	private Rank isStraightFlush(ArrayList<Card> cardDeck) {
		
		int straight=0, flush=0;
		
		
		if(((isStraight(cardDeck))) != null) {
			straight=(isStraight(cardDeck)).getGrade();
		}
		
		if(((isFlush(cardDeck))) != null) {
			flush=isFlush(cardDeck).getGrade();
		}
		
		
		if(straight==3 && flush==1) {
			System.out.println("�ξ� ��Ʈ����Ʈ �÷���");
			return new Rank(3,cardDeck.get(0).getSuit());
		}else if(straight==2 && flush==1) {
			System.out.println("�� ��Ʈ����Ʈ �÷���");
			return new Rank(2,cardDeck.get(0).getSuit());
		}else if(straight==1 && flush==1) {
			System.out.println("��Ʈ����Ʈ �÷���");
			return new Rank(1,cardDeck.get(0).getSuit());
		}else {
			return null;
		}
	}


	private Rank isPair(ArrayList<Card> cardDeck) {
		
		//count�� check���� ���� (Ǯ�Ͽ콺 �Ǵ� ��ī��), (Ʈ���� �Ǵ� �� ���), �� ���, ����ī�� �� ����
		int i=0, count=0,check=0;
		//sequence�� ���� (Ǯ�Ͽ콺 �Ǵ� ��ī��), (Ʈ���� �Ǵ� �� ���) �� �����ش�.
		int sequence=0;
		//sequence�� 2��� continue_check�� true�� �Ǿ� Ʈ����, false��� �� ���
		boolean continue_check =false;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();
		
		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;			
		}
		Arrays.sort(Straight);	

		int temp = Straight[0];
		
		for(int j = 1 ; j < Straight.length ; j++) {
			if(temp==Straight[j]) {
				count++;
				sequence++;
				if(sequence==2) {
					continue_check=true;
				}
			}else {
				temp=Straight[j];
				check++;
				sequence=0;
			}
		}
		if(count==0 && check==4) {
			System.out.println("����ī��");
			return new Rank(0,Straight[4]);
		}else if(count==1 && check==3) {
			System.out.println("�����");
			return new Rank(1,0);
		}else if(count==2 && check==2 && continue_check==false) {
			System.out.println("�����");
			return new Rank(2,0);
		}else if(count==2 && check==2 && continue_check==true) {
			System.out.println("Ʈ����");
			return new Rank(0,0);
		}else {
			return null;
		}
	}


	private Rank isStraight(ArrayList<Card> cardDeck) {

		int i=0;
		int[] Straight = new int[5];
		Iterator<Card> it = cardDeck.iterator();
		
		while (it.hasNext()) {
			Straight[i] = it.next().getRank();
			i++;			
		}
		Arrays.sort(Straight);	
		
		//����ƾ or �ξ� ��Ʈ����Ʈ �÷���
		if(Straight[0] == 0 && Straight[1] == 9 && Straight[2] == 10 
				&& Straight[3]==11 && Straight[4]==12){
			System.out.println("����ƾ");
			return new Rank(3,0);
			//�齺Ʈ����Ʈ�÷��� or �齺Ʈ����Ʈ
		}else if(Straight[0] == 0 && Straight[1] == 1 && Straight[2] == 2 
				&& Straight[3]==3 && Straight[4]==4) {
			System.out.println("�齺Ʈ����Ʈ");
			return new Rank(2,0);
			//��Ʈ����Ʈ�÷��� or ��Ʈ����Ʈ
		}else if(Straight[0] == (Straight[1]-1) && Straight[0] == (Straight[2]-2) && Straight[0] == (Straight[3]-3) 
				&& Straight[0]==(Straight[4]-4)) {
			System.out.println("��Ʈ����Ʈ");
			return new Rank(1,Straight[4]);			
		}else {
			return null;
		}
	}

	public Rank isFlush(ArrayList<Card> cardDeck) {

		int heart = 0, clover = 0, diamond = 0, spade = 0, temp;

		Iterator<Card> it = cardDeck.iterator();
		while (it.hasNext()) {
			temp = it.next().getSuit();

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

		if (spade == 5 || clover == 5 || heart == 5 || diamond == 5) {
			System.out.println("�÷�����");
			return new Rank(1,0);
		}else {
			return new Rank(0,0);
		}

	}

	public int calculation() {
		// �� ��¹� �� ����� 
		//���⿡ �º� ����� �Բ� ���
		
		return 0;
	}
}
