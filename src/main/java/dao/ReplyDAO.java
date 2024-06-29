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

import dto.BoardDTO;
import dto.MemberDTO;
import dto.ReplyDTO;

public class ReplyDAO {

	private static ReplyDAO instance;

	public static synchronized ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}

	private ReplyDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// board_seq로 해당 게시물의 댓글 목록 조회 및 매개변수로 정렬 기준 설정
	public List<ReplyDTO> selectByBoardSeq(int board_seq, String order_by) throws Exception {

		if (order_by.equals("write_date") || order_by.equals("thumbs_up")) {
			// SQL 인젝션 방지를 위해 허용된 매개 변수 값인지 검사 후 코드 실행
			String sql = "select r.*, coalesce(like_count, 0) thumbs_up from reply r left join (select reply_seq, count(*) like_count from reply_like group by reply_seq) rl on r.reply_seq = rl.reply_seq where board_seq = ? and delete_yn = 'N' order by "
					+ order_by + " desc";
			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
				pstat.setInt(1, board_seq);
				List<ReplyDTO> list = new ArrayList<>();
				try (ResultSet rs = pstat.executeQuery();) {
					while (rs.next()) {
						int seq = rs.getInt(1);
						String writer = rs.getString(2);
						String content = rs.getString(3);
						Timestamp write_date = rs.getTimestamp(4);
						String delete_yn = rs.getString(6);
						list.add(new ReplyDTO(seq, writer, content, write_date, board_seq, delete_yn));
					}
					System.out.println(list);
					return list;
				}
			}
		}
		// 허용되지 않은 매개 변수의 값인 경우 null 값 반환
		return null;
	}

	// 댓글 작성
	public boolean insert(ReplyDTO dto) throws Exception {

		String sql = "insert into reply values (reply_seq.nextval, ?, ?, sysdate, ?, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getNickname());
			pstat.setString(2, dto.getContent());
			pstat.setInt(3, dto.getBoard_seq());
			pstat.setString(4, dto.getDelete_yn());
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}

	// 댓글 삭제
	public boolean deleteBySeq(int seq) throws Exception {

		String sql = "delete from reply where reply_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;

	}

	// 댓글 수정
	public boolean updateContent(int seq, String content) throws Exception {

		String sql = "update reply set content = ? where reply_seq = ?";

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

		String sql = "update reply set thumbs_up = thumbs_up + 1 where reply_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}

	// 댓글 좋아요 취소
	public boolean replyUnLike(int seq) throws Exception {

		String sql = "update reply set thumbs_up = thumbs_up - 1 where reply_seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			if (pstat.executeUpdate() > 0)
				return true;
		}
		return false;
	}

	// 댓글 좋아요 수를 반환하는 메서드
	public int selectLikesBySeq(int reply_seq) throws Exception {

		String sql = "select thumbs_up from reply where reply_seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, reply_seq);
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}

//	마이페이지에서 회읜의 댓글 작성 수를 조회
	public int searchReplyCount(String id) throws Exception {
		String sql = "select count(*) from reply where nickname = (select nickname from member where userid = ?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);

			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 작성 수 조회 중 오류 발생", e);
		}
		return 0; // 기본값으로 0을 반환
	}

	// 삭제된 댓글의 수 조회
	public int getDeletedReplyCount() throws Exception {
		String sql = "select count(*) from reply where delete_yn = 'Y'";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {
			if (rs.next()) { // rs.next()를 호출하여 커서를 첫 번째 행으로 이동
				return rs.getInt(1);
			} else {
				return 0; // 결과가 없는 경우 0을 반환
			}

		}
	}

//	관리자가 deleteYN = Y인 삭제된게시물(임시 보관 게시물)을 조회하는 메서드
	public List<ReplyDTO> searchDeletedReply(int startNum, int endNum) throws Exception {

		String sql = "select * from (select reply.*, row_number() over(order by reply_seq desc) as rown from reply where delete_yn = 'Y') where rown between ? and ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<ReplyDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int reply_seq = rs.getInt("reply_seq");
					String nickname = rs.getString("nickname");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int board_seq = rs.getInt("board_seq");
					String delete_yn = rs.getString("delete_yn");

					list.add(new ReplyDTO(reply_seq, nickname, content, write_date, board_seq, delete_yn));
				}
				return list;
			}
		}
	}

//	해당 댓글의 deleteYN을 Y로
	public int updateToY(int reply_id) throws Exception {

		String sql = "update reply set delete_yn = 'Y' where reply_seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);

		) {

			pstat.setInt(1, reply_id);

			return pstat.executeUpdate();
		}
	}

//	해당 댓글의 deleteYN을 N으로
	public int updateToN(int reply_id) throws Exception {

		String sql = "update reply set delete_yn = 'N' where reply_seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);

		) {

			pstat.setInt(1, reply_id);

			return pstat.executeUpdate();
		}
	}

}
