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
		System.out.println("==★☆★종    합     게    임    센    터★☆★==");
		System.out.println("=================================");
		
		do {
		System.out.println("메뉴를 선택하세요 : 1.로그인  2.회원가입  3.종료");
		System.out.print("선택 : ");
		
		choice=sc.nextInt();
		
		switch (choice) {
		case 1:
			signIn();
			break;
		case 2:
			signUp();
			break;
		case 3:
			System.out.println("종료합니다.");
			break;
		default:
			System.out.println("1, 2, 3 중에서 선택해 주세요.");
			break;
		}
		}while(choice!=3);

	}

	private void signIn() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		
		System.out.println("★☆★로그인★☆★");
		System.out.print("I D : ");
		id = sc.nextLine().trim();
		System.out.print("PW : ");
		pw = sc.nextLine().trim();
		
		DBQuery query = new DBQuery();
		check = query.logIn(id, pw, check);
		
		if(check) {
			choiceGame();
		}else {
			System.out.println("로그인 실패");
		}

	}

	private void choiceGame() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("★☆★게임선택화면★☆★");
			System.out.println("게임을 선택하세요 : 1.포커  2.섯다  3.퀴즈  4.행맨  5.취소");
			System.out.print("선택 : ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("포커게임 진입");
				new PokerMain().run();
				break;
			case 2:
				System.out.println("섯다게임 진입");
				new Suttda();
				break;
			case 3:
				System.out.println("퀴즈게임 진입");
				break;
			case 4:
				System.out.println("행맨게임 진입");
				hangmanMain.run();
				break;
			case 5:
				System.out.println("취소합니다.");
				return;
			default:
				System.out.println("1~5사이의 숫자를 입력하세요.");
				break;
			}
		}while(choice!=5);
	}

	private void signUp() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		
		System.out.println("★☆★회원가입★☆★");
		
		do {
			System.out.print("I D : ");
			id = sc.nextLine().trim();
			if(id.length()<=0 ||id.length()>15) {
				System.out.println("ID는 15글자 이내의 영문+숫자만(한글은 3글자 까지) 가능합니다.");
				check=false;
			}else
				check=true;
		}while(!check);
		
		do {
			System.out.print("PW : ");
			pw = sc.nextLine().trim();
			if(pw.length()<=0 || pw.length()>15) {
				System.out.println("ID는 15글자 이내의 영문+숫자만(한글은 3글자 까지) 가능합니다.");
				check=false;
			}else
				check=true;			
		}while(!check);
		
		DBQuery query = new DBQuery();
		query.signUp(id, pw, check);
		
	}
}
