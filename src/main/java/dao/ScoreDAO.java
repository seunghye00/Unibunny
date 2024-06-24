package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ScoreDTO;

public class ScoreDAO {

	private static ScoreDAO instance;
	
	public static synchronized ScoreDAO getInstance() {
		if (instance == null) {
			instance = new ScoreDAO();
		}
		return instance;
	}

	private ScoreDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	public boolean insertScore(ScoreDTO score) throws Exception{
		boolean result = false;
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) rown from board) where rown between ? and ?";
		try (   Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);	
				) {
			pstat.setInt(1, score.getScore());
			pstat.setInt(2, score.getGame_id());
			pstat.setString(3, score.getNickname());
			pstat.setTimestamp(4, score.getEnd_time());
			pstat.setInt(5, score.getLog_seq());
			int rowsAffected = pstat.executeUpdate();
			if (rowsAffected > 0) {
				result = true;
	        }
		}
		return result;
	}
}
