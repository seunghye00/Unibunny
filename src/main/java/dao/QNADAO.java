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
import dto.QNADTO;

import dto.QNADTO;

public class QNADAO {

	private static QNADAO instance;

	public static synchronized QNADAO getInstance() {
		if (instance == null) {
			instance = new QNADAO();
		}
		return instance;
	}

	private QNADAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	
	// 전체 QNA 카운트 조회 
		public int getRecordCount() throws Exception {
			String sql = "select count(*) from QNA";
			try (
							Connection con = this.getConnection();
							PreparedStatement pstat = con.prepareStatement(sql);
					ResultSet rs= pstat.executeQuery();
							) {
					 rs.next();
					 return rs.getInt(1);
				}
		}
	
	
	

	public List<QNADTO> searchMyQNAList(int startNum, int endNum, String userid) throws Exception {
//		해당하는 유저가 작성한 QNA 리스트를 찾아서 반환하는 메서드
//		답변 여부에 상관없이, 회원이 작성한 QNA 리스트를 모두 반환한다.
	    String sql = "select * from (select qna.*, row_number() over(order by question_seq desc) rown from qna where userid = ?) where rown between ? and ?";
	    
	    try (
	        Connection con = this.getConnection();
	        PreparedStatement pstat = con.prepareStatement(sql);
	    ) {
	        List<QNADTO> list = new ArrayList<>();
	        pstat.setString(1, userid);
	        pstat.setInt(2, startNum);
	        pstat.setInt(3, endNum);

	        try (ResultSet rs = pstat.executeQuery()) {
	            while (rs.next()) {
	                int question_seq = rs.getInt("question_seq");
	                String question_title = rs.getString("question_title");
	                String question_content = rs.getString("question_content");
	                Timestamp write_date = rs.getTimestamp("write_date");
	                String answer_yn = rs.getString("answer_yn");
	                String answer_content = rs.getString("answer_content");
	                Timestamp answer_date = rs.getTimestamp("answer_date");
	                String userId = rs.getString("userid");

	                list.add(new QNADTO(question_seq, question_title, question_content, write_date, answer_yn, answer_content, answer_date, userId));
	            }
	            return list;
	        }
	    }
	}

	
	
	
	
	
	
	
	
	
	
}

