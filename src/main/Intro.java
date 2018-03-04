package main;

import java.util.Scanner;

import DB.DBQuery;
import jehwan.poker.PokerMain;
import jihong.Suttda;
import sanghee.hangmanMain;

public class Intro {
	
	public void intro() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice=0;
		
		System.out.println("=================================");
		System.out.println("==�ڡ١���    ��     ��    ��    ��    �͡ڡ١�==");
		System.out.println("=================================");
		
		do {
		System.out.println("�޴��� �����ϼ��� : 1.�α���  2.ȸ������  3.����");
		System.out.print("���� : ");
		
		choice=sc.nextInt();
		
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
		
		System.out.println("�ڡ١ڷα��Ρڡ١�");
		System.out.print("I D : ");
		id = sc.nextLine().trim();
		System.out.print("PW : ");
		pw = sc.nextLine().trim();
		
		DBQuery query = new DBQuery();
		check = query.logIn(id, pw, check);
		
		if(check) {
			choiceGame();
		}else {
			System.out.println("�α��� ����");
		}

	}

	private void choiceGame() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("�ڡ١ڰ��Ӽ���ȭ��ڡ١�");
			System.out.println("������ �����ϼ��� : 1.��Ŀ  2.����  3.����  4.���  5.���");
			System.out.print("���� : ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("��Ŀ���� ����");
				new PokerMain().run();
				break;
			case 2:
				System.out.println("���ٰ��� ����");
				new Suttda();
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
		
		do {
			System.out.print("PW : ");
			pw = sc.nextLine().trim();
			if(pw.length()<=0 || pw.length()>15) {
				System.out.println("ID�� 15���� �̳��� ����+���ڸ�(�ѱ��� 3���� ����) �����մϴ�.");
				check=false;
			}else
				check=true;			
		}while(!check);
		
		DBQuery query = new DBQuery();
		query.signUp(id, pw, check);
		
	}
}
