package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
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

	public int insertQnA(QNADTO dto) throws Exception {
		String sql = "INSERT INTO QNA (QUESTION_SEQ, QUESTION_TITLE, QUESTION_CONTENT, WRITE_DATE, USERID, ANSWER_CONTENT) VALUES (QUESTION_SEQ.NEXTVAL, ?, ?, ?, ?, '')";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getQuestion_title());
			ps.setString(2, dto.getQuestion_content());
			ps.setTimestamp(3, dto.getWrite_date());
			ps.setString(4, dto.getId());
			return ps.executeUpdate();
		}
	}
	
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
}

