package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DB.DBConnection;

public class Intro {
	
	public void intro() {
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
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		
		System.out.println("★☆★로그인★☆★");
		System.out.print("I D : ");
		id = sc.nextLine().trim();
		System.out.print("PW : ");
		pw = sc.nextLine().trim();
		
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
		PreparedStatement pstm = null; // SQL 문을 나타내는 객체
		ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

		try {
			// SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
			// 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
			String quary = "SELECT * FROM TCM_USER";

			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String dbId = rs.getString(1);			
				String dbPw = rs.getString(2);


				if(id.equals(dbId) && pw.equals(dbPw)) {
					System.out.println("로그인 성공");
					check=true;
					break;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("SELECT문에서 예외 발생");
			sqle.printStackTrace();

		} finally {
			// DB 연결을 종료한다.
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		if(check) {
			choiceGame();
		}else {
			System.out.println("로그인 실패");
		}

	}

	private void choiceGame() {
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
				break;
			case 2:
				System.out.println("섯다게임 진입");
				break;
			case 3:
				System.out.println("퀴즈게임 진입");
				break;
			case 4:
				System.out.println("행맨게임 진입");
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
		
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String quary1 = "INSERT INTO TCM_USER VALUES ( '" + id + "', '" + pw + "')";
            String quary2 = "SELECT * FROM TCM_USER";

            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary2);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
				String dbId = rs.getString(1);			

				if(id.equals(dbId)) {
					System.out.println("회원가입 실패");
					System.out.println("현재 해당 아이디가 존재합니다.");
					check=false;
					break;
				}
           // System.out.println("회원가입 성공");
            }
            if(check) {
            	System.out.println("회원가입 성공");
            	conn.prepareStatement(quary1).executeQuery();              
            }else {
            	return;
            }
        } catch (SQLException sqle) {
            System.out.println("INSERT문에서 예외 발생");
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }            
        }        
	}
}
