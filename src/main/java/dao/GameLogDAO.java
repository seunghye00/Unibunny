package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		String sql = "insert into gamelog (log_seq, start_time, game_id, nickname) values (log_seq.nextval, sysdate, ?, ?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameID);
			pstat.setString(2, nickname);
			ResultSet generatedKeys = pstat.getGeneratedKeys();
		        if (generatedKeys.next()) {
		        	// 이 부분에서 자동 생성된 log_seq 값을 가져옴
		        	 return generatedKeys.getInt(1);  
		        } else {
		        	// 로그 가져오는데 실패 시 오류 메시지
		            throw new Exception("게임 로그 삽입 중에 자동 생성된 log_seq 값을 가져오는데 실패했습니다.");
		        }
		}
	}
}
