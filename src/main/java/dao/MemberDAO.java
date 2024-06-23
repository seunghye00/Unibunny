package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
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
		String sql = "select *from  member where  userid=? and pw=? ";
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
    
    

    
public MemberDTO searchProfileInfo(String id) throws Exception{
//  마이페이지 프로필 정보 조회(해당하는 아이디의 닉네임,가입날짜 등)
	
		String sql = "select * from member where userid = ?";
	
			
		try(
				Connection con = this.getConnection();	
				PreparedStatement pstat = con.prepareStatement(sql);
				
				){
			pstat.setString(1,id);
			
			
			try(ResultSet rs = pstat.executeQuery();){

			while(rs.next()) {
				
				String userid = rs.getString("userid");
				String nickname = rs.getString("nickname");
				String pw = rs.getString("pw");
				String phone = rs.getString("phone");
				String reg_num = rs.getString("reg_num");
				String email = rs.getString("email");
				String postcode= rs.getString("postcode");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				Timestamp join_date = rs.getTimestamp("join_date");
				int memcode = rs.getInt("memcode");
				String profile_img = rs.getString("profile_img");
				
				MemberDTO dto = new MemberDTO(userid,nickname,pw,phone,reg_num,email,postcode,address1,address2,join_date,memcode,profile_img);
				return dto;		
			}
		
			}
		}
		return null;
	}
    

	public int updateUserInfo(MemberDTO dto) throws Exception {
//	해당 유저의 id로 회원의 정보를 수정한다.
//	마이 페이지의 계정 관리 기능
			String sql = "update member set nickname = ?, pw = ?, phone = ?, email = ?, address1 = ?, address2 = ?, postcode = ? where userid = ?";
			try(
					Connection con = this.getConnection();	
					PreparedStatement pstat = con.prepareStatement(sql);
			 
					){
		
				pstat.setString(1, dto.getNickname());
				pstat.setString(2, dto.getPw());
				pstat.setString(3, dto.getPhone());
				pstat.setString(4, dto.getEmail());
				pstat.setString(5, dto.getPostcode());
				pstat.setString(6, dto.getAddress1());
				pstat.setString(7, dto.getAddress2());
				pstat.setString(8, dto.getUserid());
			
	
				return pstat.executeUpdate();}
		}


	
	public int deleteMember(String id) throws Exception {
//		해당 회원의 정보를 삭제한다.
//		마이페이지의 회원 탈퇴 기능
		String sql = "delete from member where userid = ?";
		try(
				Connection con = this.getConnection();	
				PreparedStatement pstat = con.prepareStatement(sql);
				 
				){	
			pstat.setString(1,id);
			int result = pstat.executeUpdate();
		return result;
		}
	}
	

    
    
    
}
