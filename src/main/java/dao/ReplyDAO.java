package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ReplyDTO;

public class ReplyDAO {
	
	private static ReplyDAO instance;
	
	public static synchronized ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}

	private ReplyDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// target_seq가 board_seq인 댓글 목록 조회
	public List<ReplyDTO> selectByBoardSeq(int target_seq) throws Exception {

		String sql = "select * from reply where board_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, target_seq);
			List<ReplyDTO> list = new ArrayList<>();
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int seq = rs.getInt(1);
					String writer = rs.getString(2);
					String content = rs.getString(3);
					Timestamp write_date = rs.getTimestamp(4);
					int thumbs_up = rs.getInt(5);
					int board_seq = rs.getInt(6);
					list.add(new ReplyDTO(seq, writer, content, write_date, thumbs_up ,board_seq));
				}
				return list;
			}
		}
	}

	// 댓글 작성
	public boolean insert(ReplyDTO dto) throws Exception {
		
		String sql = "insert into reply values (reply_seq.nextval, ?, ?, sysdate, 0, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getNickname());
			pstat.setString(2, dto.getContent());
			pstat.setInt(3, dto.getBoard_seq());
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}

	// 댓글 삭제
	public boolean deleteBySeq(int seq) throws Exception {
		
		String sql = "delete from reply where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
		
	}

	// 댓글 수정
	public boolean updateContent(int seq, String content) throws Exception {
		
		String sql = "update reply set content = ? where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, content);
			pstat.setInt(2, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}
	
	// 댓글 좋아요
	public boolean replyLike(int seq) throws Exception {
		
		String sql = "update reply set thumbs_up = thumbs_up + 1 where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}
	
	// 댓글 좋아요 취소
	public boolean replyUnLike(int seq) throws Exception {
		
		String sql = "update reply set thumbs_up = thumbs_up - 1 where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}
}
