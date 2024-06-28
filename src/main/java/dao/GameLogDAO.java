package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GameLogDAO {

	private static GameLogDAO instance;

	public static synchronized GameLogDAO getInstance() {
		if (instance == null) {
			instance = new GameLogDAO();
		}
		return instance;
	}

	private GameLogDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int insertGameLog(int gameID, String nickname) throws Exception {
	    System.out.println(gameID);
	    System.out.println(nickname);
	    String sql = "insert into gamelog (log_seq, start_time, game_id, nickname) values (log_seq.nextval, sysdate, ?, ?)";

	    try (Connection con = this.getConnection(); 
	         PreparedStatement pstat = con.prepareStatement(sql, new String[]{"LOG_SEQ"})) { // 컬럼 이름 대문자로 지정
	        
	        pstat.setInt(1, gameID);
	        pstat.setString(2, nickname);
	        
	        int affectedRows = pstat.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new Exception("게임 로그 삽입에 실패했습니다.");
	        }
	        
	        try (ResultSet generatedKeys = pstat.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                // 자동 생성된 log_seq 값을 숫자로 가져옴
	                return generatedKeys.getInt(1); // 이 경우 컬럼 인덱스를 사용
	            } else {
	                throw new Exception("게임 로그 삽입 중에 자동 생성된 log_seq 값을 가져오는데 실패했습니다.");
	            }
	        }
	    }
	}

}
