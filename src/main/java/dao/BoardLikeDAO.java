package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonElement;

public class BoardLikeDAO {

	private static BoardLikeDAO instance;

	public static synchronized BoardLikeDAO getInstance() {
		if (instance == null) {
			instance = new BoardLikeDAO();
		}
		return instance;
	}

	private BoardLikeDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// 해당 게시글의 좋아요 수를 반환하는 함수
	public int countByBoardSeq(int board_seq) throws Exception {

		String sql = "select count(*) from board_like where board_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			try (ResultSet rs = pstat.executeQuery();) {
				if (rs.next())
					return rs.getInt(1);
			}
		}
		// 결과가 존재하지 않는다면 0 반환
		return 0;
	}

	// 해당 게시글 좋아요 기능
	public int insertRecord(int board_seq, String user_id) throws Exception {

		String sql = "insert into board_like values (board_like_seq.nextval, ?, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, user_id);
			pstat.setInt(2, board_seq);
			return pstat.executeUpdate();
		}
	}

	// 해당 게시글 좋아요 취소 기능
	public int deleteRecord(int board_seq, String user_id) throws Exception {

		String sql = "delete from board_like where board_seq = ? and userid = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			pstat.setString(2, user_id);
			return pstat.executeUpdate();
		}
	}

	// 유저가 해당 게시글 좋아요 기능을 사용했는 지 여부 확인 메서드
	public boolean checkLog(int board_seq, String user_id) throws Exception {

		String sql = "select * from board_like where board_seq = ? and userid = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			pstat.setString(2, user_id);
			try (ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}
		}
	}
}
