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

	private ScoreDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public boolean insertScore(ScoreDTO dto) throws Exception {
		String sql = "insert into score (score_seq, score, game_id, nickname, end_time, log_seq) values (score_seq.nextval, ?, ?, ?, sysdate, ?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getScore());
			pstat.setInt(2, dto.getGame_id());
			pstat.setString(3, dto.getNickname());
			pstat.setInt(4, dto.getLog_seq());
			int rowsAffected = pstat.executeUpdate();
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}

	public List<ScoreDTO> selectListGame(int gamelistNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select s.*, m.profile_img from score s join member m on s.nickname = m.nickname where s.game_id = ? order by s.score desc";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, gamelistNum);

			List<ScoreDTO> list = new ArrayList<>();
			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {
					int score_seq = rs.getInt("score_seq");
					int score = rs.getInt("score");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					Timestamp end_time = rs.getTimestamp("end_time");
					int log_seq = rs.getInt("log_seq");
					String profile_img = rs.getString("profile_img");
					// profile_img 설정

					ScoreDTO scoreDTO = new ScoreDTO(score_seq, score, game_id, nickname, end_time, log_seq,
							profile_img);
					list.add(scoreDTO);
				}
				return list;
			}
		}
	}
}
