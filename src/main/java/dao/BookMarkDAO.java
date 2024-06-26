package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonElement;

public class BookMarkDAO {

	private static BookMarkDAO instance;

	public static synchronized BookMarkDAO getInstance() {
		if (instance == null) {
			instance = new BookMarkDAO();
		}
		return instance;
	}

	private BookMarkDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// board_seq 값으로 해당 글의 북마크 수를 조회하는 메서드
	public int selectByBoardSeq(int board_seq) throws Exception {

		String sql = "select count(*) from bookmark where board_seq = ?";
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

	// board_seq와 getSession()값을 통해 id에 북마크 저장
	public int saveBookMark(String user_id, int board_seq) throws Exception {
		String sql = "insert into bookmark values(bookmark_seq.nextval, ?, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, user_id);
			pstat.setInt(2, board_seq);
			int result = pstat.executeUpdate();
			return result;
		}
	}
	// board_seq와 getSession()값을 통해 id에 북마크 삭제
	public int unsaveBookMark(String user_id, int board_seq) throws Exception {
		String sql = "delete from bookmark where userid = ? and board_seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, user_id);
			pstat.setInt(2, board_seq);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	// 유저가 해당 게시글 북마크 기능을 사용했는 지 여부 확인 메서드
	public boolean checkLog(int board_seq, String user_id) throws Exception {

		String sql = "select * from bookmark where board_seq = ? and userid = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			pstat.setString(2, user_id);
			try (ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}
		}
	}
}
