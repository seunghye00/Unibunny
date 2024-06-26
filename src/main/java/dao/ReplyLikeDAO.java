package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyLikeDAO {

	   private static ReplyLikeDAO instance;
	   
	   public static synchronized ReplyLikeDAO getInstance() {
	      if (instance == null) {
	         instance = new ReplyLikeDAO();
	      }
	      return instance;
	   }

	   private ReplyLikeDAO() {}

	   private Connection getConnection() throws Exception {
	      Context ctx = new InitialContext();
	      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
	      return ds.getConnection();
	   }
	   
		// 해당 댓글의 좋아요 수를 반환하는 함수
		public int countByReplySeq(int reply_seq) throws Exception {

			String sql = "select count(*) from reply_like where reply_seq = ?";

			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
				pstat.setInt(1, reply_seq);
				try (ResultSet rs = pstat.executeQuery();) {
					if (rs.next())
						return rs.getInt(1);
				}
			}
			// 결과가 존재하지 않는다면 0 반환
			return 0;
		}

		// 해당 댓글 좋아요 기능
		public int insertRecord(int reply_seq, String user_id) throws Exception {

			String sql = "insert into reply_like values (reply_like_seq.nextval, ?, ?)";

			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
				pstat.setString(1, user_id);
				pstat.setInt(2, reply_seq);
				return pstat.executeUpdate();
			}
		}

		// 해당 댓글 좋아요 취소 기능
		public int deleteRecord(int reply_seq, String user_id) throws Exception {

			String sql = "delete from reply_like where reply_seq = ? and userid = ?";

			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
				pstat.setInt(1, reply_seq);
				pstat.setString(2, user_id);
				return pstat.executeUpdate();
			}
		}

		// 유저가 해당 댓글 좋아요 기능을 사용했는 지 여부 확인 메서드
		public boolean checkLog(int reply_seq, String user_id) throws Exception {

			String sql = "select * from reply_like where reply_seq = ? and userid = ?";

			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
				pstat.setInt(1, reply_seq);
				pstat.setString(2, user_id);
				try (ResultSet rs = pstat.executeQuery();) {
					return rs.next();
				}
			}
		}
}
