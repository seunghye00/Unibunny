package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FAQDTO;

public class FAQDAO {

    private static FAQDAO instance;

    public static synchronized FAQDAO getInstance() {
        if (instance == null) {
            instance = new FAQDAO();
        }
        return instance;
    }

    private FAQDAO() {}

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    // FAQ 등록하기
    public int insertFAQ(FAQDTO faq) throws Exception {
        String sql = "INSERT INTO faq (faq_seq, title, content) VALUES (faq_seq.NEXTVAL, ?, ?)";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, faq.getTitle());
            ps.setString(2, faq.getContent());
            int result = ps.executeUpdate();
            return result;
        }
    }

    // FAQ 조회하기
    // 사용자, 관리자 둘 다 전체를 뽑아와야한다!
    public List<FAQDTO> getFAQs() throws Exception {
        String sql = "SELECT * FROM faq ORDER BY faq_seq DESC";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<FAQDTO> faqList = new ArrayList<>();
            while (rs.next()) {
                FAQDTO faq = new FAQDTO();
                faq.setFaq_seq(rs.getInt("faq_seq"));
                faq.setTitle(rs.getString("title"));
                faq.setContent(rs.getString("content"));
                faqList.add(faq);
            }
            return faqList;
        }
    }
    
    //FAQ 삭제하기
    public int deleteFAQ(int id) throws Exception {
        String sql = "DELETE FROM faq WHERE faq_seq = ?";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            return result;
        }
    }
}
