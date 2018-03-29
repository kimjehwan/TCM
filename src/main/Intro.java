package main;

import java.util.Scanner;

import DB.DBQuery;
import jehwan.poker.PokerMain;
import jihong.suttda.SuttdaMain;
import sanghee.hangman.hangmanMain;

public class Intro {
	
	public void intro() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice=0;
		
		System.out.println("=================================");
		System.out.println("==�ڡ١���    ��     ��    ��    ��    �͡ڡ١�==");
		System.out.println("=================================");
		//�α��� ȭ��
		do {
		System.out.println("�޴��� �����ϼ��� : 1.�α���  2.ȸ������  3.����");
		System.out.print("���� : ");
		
		choice=sc.nextInt();
		//���ÿ� ���� �ٸ� �̺�Ʈ�� �����Ѵ�.
		switch (choice) {
		case 1:
			signIn();
			break;
		case 2:
			signUp();
			break;
		case 3:
			System.out.println("�����մϴ�.");
			break;
		default:
			System.out.println("1, 2, 3 �߿��� ������ �ּ���.");
			break;
		}
		}while(choice!=3);

	}

	private void signIn() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		//�α���â�� �����ش�.
		System.out.println("�ڡ١ڷα��Ρڡ١�");
		System.out.print("I D : ");
		id = sc.nextLine().trim();
		System.out.print("PW : ");
		pw = sc.nextLine().trim();
		//DB�� ���̵�� ��й�ȣ�� �����Ѵ�.
		DBQuery query = new DBQuery();
		check = query.logIn(id, pw, check);
		
		if(check) {
			choiceGame();
		}else {
			System.out.println("�α��� ����");
		}

	}
	//���ÿ� ���� �ش��ϴ� �������� �Ѿ�� �޼ҵ�
	private void choiceGame() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("�ڡ١ڰ��Ӽ���ȭ��ڡ١�");
			System.out.println("������ �����ϼ��� : 1.��Ŀ  2.����  3.����  4.���  5.���");
			System.out.print("���� : ");
			choice = sc.nextInt();
			//������ ��ȣ�� ���� ������ ���ð����ϵ��� ����
			switch (choice) {
			case 1:
				System.out.println("��Ŀ���� ����");
				new PokerMain().run();
				break;
			case 2:
				System.out.println("���ٰ��� ����");
				new SuttdaMain();
				break;
			case 3:
				System.out.println("������� ����");
				break;
			case 4:
				System.out.println("��ǰ��� ����");
				hangmanMain.run();
				break;
			case 5:
				System.out.println("����մϴ�.");
				return;
			default:
				System.out.println("1~5������ ���ڸ� �Է��ϼ���.");
				break;
			}
		}while(choice!=5);
	}
	//ȸ�������� �ϰ� �� ������� DB�� �����ϵ��� �ϴ� �޼ҵ� 
	private void signUp() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		
		System.out.println("�ڡ١�ȸ�����ԡڡ١�");
		
		do {
			System.out.print("I D : ");
			id = sc.nextLine().trim();
			if(id.length()<=0 ||id.length()>15) {
				System.out.println("ID�� 15���� �̳��� ����+���ڸ�(�ѱ��� 3���� ����) �����մϴ�.");
				check=false;
			}else
				check=true;
		}while(!check);
		//���̵��� ���̴� 1�̻� 15���Ϸ� �Ѵ�.
		do {
			System.out.print("PW : ");
			pw = sc.nextLine().trim();
			if(pw.length()<=0 || pw.length()>15) {
				System.out.println("ID�� 15���� �̳��� ����+���ڸ�(�ѱ��� 3���� ����) �����մϴ�.");
				check=false;
			}else
				check=true;			
		}while(!check);
		//��й�ȣ�� ���̴� 1�̻� 15���Ϸ� �Ѵ�
		DBQuery query = new DBQuery();
		query.signUp(id, pw, check);
		
	}
}
