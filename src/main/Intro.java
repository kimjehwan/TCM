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
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check=false;
		
		System.out.println("�ڡ١ڷα��Ρڡ١�");
		System.out.print("I D : ");
		id = sc.nextLine().trim();
		System.out.print("PW : ");
		pw = sc.nextLine().trim();
		
		Connection conn = null; // DB����� ����(����)�� ���� ��ü
		PreparedStatement pstm = null; // SQL ���� ��Ÿ���� ��ü
		ResultSet rs = null; // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü

		try {
			// SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
			// �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
			String quary = "SELECT * FROM TCM_USER";

			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String dbId = rs.getString(1);			
				String dbPw = rs.getString(2);


				if(id.equals(dbId) && pw.equals(dbPw)) {
					System.out.println("�α��� ����");
					check=true;
					break;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();

		} finally {
			// DB ������ �����Ѵ�.
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
			System.out.println("�α��� ����");
		}

	}

	private void choiceGame() {
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
				break;
			case 2:
				System.out.println("���ٰ��� ����");
				break;
			case 3:
				System.out.println("������� ����");
				break;
			case 4:
				System.out.println("��ǰ��� ����");
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
		
		Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
            String quary1 = "INSERT INTO TCM_USER VALUES ( '" + id + "', '" + pw + "')";
            String quary2 = "SELECT * FROM TCM_USER";

            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary2);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
				String dbId = rs.getString(1);			

				if(id.equals(dbId)) {
					System.out.println("ȸ������ ����");
					System.out.println("���� �ش� ���̵� �����մϴ�.");
					check=false;
					break;
				}
           // System.out.println("ȸ������ ����");
            }
            if(check) {
            	System.out.println("ȸ������ ����");
            	conn.prepareStatement(quary1).executeQuery();              
            }else {
            	return;
            }
        } catch (SQLException sqle) {
            System.out.println("INSERT������ ���� �߻�");
            sqle.printStackTrace();
            
        }finally{
            // DB ������ �����Ѵ�.
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
