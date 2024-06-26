package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
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
	public List<ScoreDTO> selectListGame(int gamelistNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from score where game_id = ? order by score desc";
		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);) 
		{	pstat.setInt(1, gamelistNum);
			List<ScoreDTO> list = new ArrayList<>();
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int score_seq = rs.getInt("score_seq");
					int score = rs.getInt("score");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					Timestamp end_time = rs.getTimestamp("end_time");
					int log_seq = rs.getInt("log_seq");
					list.add(new ScoreDTO(score_seq, score, game_id, nickname, end_time, log_seq));
				}
				return list;
			}
		}
	}
//	public static void main(String[] args) {
//        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
//        String id = "bunny"; // 데이터베이스 사용자 이름
//        String pw = "bunny"; // 데이터베이스 암호
//
//        // SQL 문: 더미 데이터를 삽입하는 SQL 문
//        String sql = "INSERT INTO Score (SCORE_SEQ, SCORE, GAME_ID, NICKNAME, END_TIME, LOG_SEQ) " +
//                     "VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (Connection con = DriverManager.getConnection(url, id, pw);
//             PreparedStatement pstmt = con.prepareStatement(sql)) {
//
//            // 50개의 더미 데이터 삽입
//            for (int i = 1; i <= 50; i++) {
//                pstmt.setInt(1, i); // SCORE_SEQ
//                pstmt.setInt(2, (int) (Math.random() * 1000)); // SCORE (0부터 999 사이의 랜덤 값)
//                pstmt.setInt(3, (int) (Math.random() * 5) + 1); // GAME_ID (1부터 10 사이의 랜덤 값)
//                pstmt.setString(4, "Nickname_" + i); // NICKNAME
//                pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // END_TIME (현재 시각)
//                pstmt.setInt(6, i); // LOG_SEQ
//
//                pstmt.executeUpdate(); // 쿼리 실행하여 데이터베이스에 삽입
//            }
//
//            System.out.println("더미 데이터 삽입 완료!");
//
//        } catch (Exception e) {
//            e.printStackTrace(); // 예외 발생 시 콘솔에 출력
//        }
//    }
}
