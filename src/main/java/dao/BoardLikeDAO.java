package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	public int insertRecord(int board_seq, String loginID) throws Exception {

		String sql = "insert into board_like values (board_like_seq.nextval, ?, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, loginID);
			pstat.setInt(2, board_seq);
			return pstat.executeUpdate();
		}
	}

	// 해당 게시글 좋아요 취소 기능
	public int deleteRecord(int board_seq) throws Exception {
		
		String sql = "delete from board_like where board_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			return pstat.executeUpdate();
		}
	}
}
