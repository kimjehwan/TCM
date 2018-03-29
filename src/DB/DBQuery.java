package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Player;

public class DBQuery 
{
	public boolean logIn(String id, String pw, boolean check) {
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("입력받은 id : "+id);
		System.out.println("입력받은 pw : "+pw);
		System.out.println("입력받은 check : "+check);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
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
				int dbPscore = rs.getInt(3);
				int dbSscore = rs.getInt(4);
				int dbQscore = rs.getInt(5);


				if(id.equals(dbId) && pw.equals(dbPw)) {
					new Player(dbId,dbPscore,dbSscore,dbQscore);
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
		return check;
	}
	
	public boolean signUp(String id, String pw, boolean check) {
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String quary1 = "INSERT INTO TCM_USER VALUES ( '" + id + "', '" + pw + "',1000,1000,1000)";
            String quary2 = "SELECT * FROM TCM_USER";

            conn = DBConnection.getConnection();
            //select쿼리를 먼저 실행 후
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
            }
            if(check) {
            	System.out.println("회원가입 성공");
            	//일치하는  ID가 없다면 insert 쿼리문을 실행하여 데이터를 삽입
            	conn.prepareStatement(quary1).executeQuery();   
            	check=true;
            }else {
            	check=false;
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
		return check;  
	}
	
	public static void savePoker() {
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            //포커 점수를 업데이트 하는 쿼리
            String quary1 = "UPDATE TCM_USER SET PSCORE = " + Player.getpScore() + " WHERE ID = '" + Player.getId() + "'";

            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary1);
            rs = pstm.executeQuery();
            
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
