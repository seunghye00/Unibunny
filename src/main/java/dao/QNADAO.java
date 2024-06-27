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
	public int insertQnA(QNADTO dto) throws Exception {
		String sql = "INSERT INTO QNA (QUESTION_SEQ, QUESTION_TITLE, QUESTION_CONTENT, WRITE_DATE, USERID, ANSWER_CONTENT) VALUES (QUESTION_SEQ.NEXTVAL, ?, ?, ?, ?, '')";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getQuestion_title());
			ps.setString(2, dto.getQuestion_content());
			ps.setTimestamp(3, dto.getWrite_date());
			ps.setString(4, dto.getUserid());
			return ps.executeUpdate();
		}
	}
	
	// DB에 마지막으로 등록된 레코드의 ID 가져오기
    public int getLastInsertedId() throws Exception {
        String sql = "SELECT QUESTION_SEQ.CURRVAL FROM DUAL";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new Exception("Failed to retrieve last inserted ID.");
            }
        }
    }
    
    // Q&A 조회하기
    public List<QNADTO> selectAllQnA() throws Exception {
    	String sql = "SELECT * FROM QNA ORDER BY answer_yn ASC, write_date ASC";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<QNADTO> list = new ArrayList<>();
            while (rs.next()) {
                QNADTO dto = new QNADTO();
                dto.setQuestion_seq(rs.getInt("QUESTION_SEQ"));
                dto.setQuestion_title(rs.getString("QUESTION_TITLE"));
                dto.setQuestion_content(rs.getString("QUESTION_CONTENT"));
                dto.setWrite_date(rs.getTimestamp("WRITE_DATE"));
                dto.setAnswer_yn(rs.getString("ANSWER_YN"));
                dto.setAnswer_content(rs.getString("ANSWER_CONTENT"));
                dto.setAnswer_date(rs.getTimestamp("ANSWER_DATE"));
                dto.setUserid(rs.getString("USERID"));
                list.add(dto);
            }
            return list;
        }
    }
    
    // Q&A 디테일
    public QNADTO selectQnABySeq(int question_seq) throws Exception {
        String sql = "SELECT * FROM QNA WHERE QUESTION_SEQ = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, question_seq);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    QNADTO dto = new QNADTO();
                    dto.setQuestion_seq(rs.getInt("QUESTION_SEQ"));
                    dto.setQuestion_title(rs.getString("QUESTION_TITLE"));
                    dto.setQuestion_content(rs.getString("QUESTION_CONTENT"));
                    dto.setWrite_date(rs.getTimestamp("WRITE_DATE"));
                    dto.setAnswer_yn(rs.getString("ANSWER_YN"));
                    dto.setAnswer_content(rs.getString("ANSWER_CONTENT"));
                    dto.setAnswer_date(rs.getTimestamp("ANSWER_DATE"));
                    dto.setUserid(rs.getString("USERID"));
                    return dto;
                } else {
                    return null;
                }
            }
        }
    }
    
    // 사용자의 Q&A에 대한 관리자의 답변 DB에 등록하기
    public int insertAnswer(QNADTO dto) throws Exception {
        String sql = "UPDATE QNA SET ANSWER_CONTENT = ?, ANSWER_YN = ?, ANSWER_DATE = ? WHERE QUESTION_SEQ = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getAnswer_content());
            ps.setString(2, dto.getAnswer_yn());
            ps.setTimestamp(3, dto.getAnswer_date());
            ps.setInt(4, dto.getQuestion_seq());
            return ps.executeUpdate();
        }
    }
}

