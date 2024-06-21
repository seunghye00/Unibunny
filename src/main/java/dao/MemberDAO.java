package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {
	public static MemberDAO instance;

	public synchronized static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	private MemberDAO() {
	};

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public int insert(MemberDTO dto) throws Exception {
	    String sql = "INSERT INTO member (USERID, NICKNAME, PW, PHONE, REG_NUM, EMAIL, POSTCODE, ADDRESS1, ADDRESS2, JOIN_DATE, MEMCODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,sysdate, ?)";
	    try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, dto.getUserid());
	        pst.setString(2, dto.getNickname());
	        pst.setString(3, dto.getPw());
	        pst.setString(4, dto.getPhone());
	        pst.setString(5, dto.getReg_num());
	        pst.setString(6, dto.getEmail());
	        pst.setString(7, dto.getPostcode());
	        pst.setString(8, dto.getAddress1());
	        pst.setString(9, dto.getAddress2());
	        pst.setInt(10, dto.getMemcode());
	        
	        return pst.executeUpdate();
	        
	    }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}


	public boolean login(String userid, String pw) throws Exception {
		String sql = "select *from  members where  id=? and pw=? ";
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, userid);
			pst.setString(2, pw);
			try (ResultSet rs = pst.executeQuery();) {
				return rs.next();
			}
		}
	}
	
//    public boolean isIdExist(String userid, String nickname ,String phone,String email) throws Exception {
//        String sql = "SELECT * FROM member WHERE userid = ? or nickname=? or phone=? or email=?" ;
//        try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
//            pst.setString(1, userid);
//            pst.setString(2, nickname);
//            pst.setString(3, phone);
//            pst.setString(4, email);
//            try (ResultSet rs = pst.executeQuery()) {
//                return rs.next();
//            }
//        }
//    }

   
    public boolean isExist(Duptype dup, String value ) throws Exception {
    	String sql = "SELECT * FROM member WHERE" ;
    	String column = "";
    	if(dup == Duptype.Userid) {
    		column = "userid = ?";
    	}
    	else if (dup == Duptype.Nickname) {
    		column = "nickname = ?";
    	}
    	else if(dup == Duptype.Phone) {
    		column = "phone = ?";
    	}
    	else if(dup == Duptype.Email) {
    		column = "email = ?";
    	}
    	else {
    		return false;
    	}
    	
        try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
        	pst.setString(1, value);
        	try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }
}
