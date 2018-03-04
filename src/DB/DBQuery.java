package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Player;

public class DBQuery 
{
	public boolean logIn(String id, String pw, boolean check) {
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
				int dbPscore = rs.getInt(3);
				int dbSscore = rs.getInt(4);
				int dbQscore = rs.getInt(5);


				if(id.equals(dbId) && pw.equals(dbPw)) {
					new Player(dbId,dbPscore,dbSscore,dbQscore);
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
		return check;
	}
	
	public void signUp(String id, String pw, boolean check) {
		Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
            String quary1 = "INSERT INTO TCM_USER VALUES ( '" + id + "', '" + pw + "',1000,1000,1000)";
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
	
	public static void savePoker() {
		Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
            String quary1 = "UPDATE TCM_USER SET PSCORE = " + Player.getpScore() + " WHERE ID = '" + Player.getId() + "'";

            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary1);
            rs = pstm.executeQuery();
            
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