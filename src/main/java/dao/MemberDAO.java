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

	//회원가입
	public int insert(MemberDTO dto) throws Exception {
		String sql = "INSERT INTO member (USERID, NICKNAME, PW, PHONE, REG_NUM, EMAIL, POSTCODE, ADDRESS1, ADDRESS2, JOIN_DATE, MEMCODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,sysdate, ?)";
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, dto.getUserid());
			System.out.println("aa");
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

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	//로그인 
	public boolean login(String userid, String pw) throws Exception {
		String sql = "select * from  member where  userid=? and pw=? ";
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, userid);
			pst.setString(2, pw);
			try (ResultSet rs = pst.executeQuery();) {
				return rs.next();
			}
		}
	}

	//회원가입 정규표현식
	public boolean isExist(Duptype dup, String value) throws Exception {
		String sql = "SELECT * FROM member WHERE ";
		String column = "";
		if (dup == Duptype.Userid) {
			column = "userid = ?"; // 그럼 여기 sql문에 ? 부분에 value값이 들어가서 쿼리됨. 그리고 이 값을 true, false 값 리턴시킴.
		} else if (dup == Duptype.Nickname) {
			column = "nickname = ?";
		} else if (dup == Duptype.Phone) {
			column = "phone = ?";
		} else if (dup == Duptype.Email) {
			column = "email = ?";
		} else {
			return false;
		}

		sql += column;
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, value);
			try (ResultSet rs = pst.executeQuery()) {
				return rs.next();
			}
		}
	}
//아이디 찾기
	public String findAccount(String reg_num, String email, String phone) throws Exception {
	    String sql = "SELECT userid FROM member WHERE reg_num = ? AND email = ? AND phone = ?";
	    
	    try (Connection con = this.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, reg_num);
	        pst.setString(2, email);
	        pst.setString(3, phone);

	        try (ResultSet rs = pst.executeQuery()) {
	            if(rs.next()) {
	            	return rs.getString("USERID");
	            }
	            else {
	            	return "";
	            }
	        }
	        catch(SQLException e) {
	        	e.printStackTrace();
	        	return "";
	        }
	    }
	}
	//비밀번호 찾기
	   public boolean findPassword(String userid, String newPassword, String email, String reg_num) throws Exception {
	        String sql = "SELECT reg_num FROM member WHERE userid = ? AND email = ?";
	        String regNum = reg_num;
	        try (Connection con = this.getConnection();
		             PreparedStatement pst = con.prepareStatement(sql)) {
		            pst.setString(1, userid);
		            pst.setString(2, email);
		            
		            try (ResultSet rs = pst.executeQuery()) {
						if(rs.next()) {
							String db_value = rs.getString("REG_NUM");
							if(regNum.indexOf("-") != -1) {
								regNum = regNum.substring(regNum.indexOf("-"));
							}
							String substr_dbVal = db_value.substring(0,db_value.indexOf("-"));
							if(substr_dbVal.equals(regNum)) {
								regNum = db_value;
							}
							else {
								return false;
							}
						}
						else {
							return false;
						}
					}
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            return false;
		        }
		   
		    sql = "UPDATE member SET pw = ? WHERE userid = ? AND email = ? AND reg_num = ?";
	        
	        try (Connection con = this.getConnection();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, newPassword);
	            pst.setString(2, userid);
	            pst.setString(3, email);
	            pst.setString(4, regNum);

	            int rowsUpdated = pst.executeUpdate();
	            return rowsUpdated > 0; // 업데이트가 성공적으로 수행되었는지 여부 반환

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	   
//	   public void kakaoLogin(MemberDTO dto) throws SQLException {
//	        String sql = "INSERT INTO member (USERID, NICKNAME, PW, PHONE, REG_NUM, EMAIL, POSTCODE, ADDRESS1, ADDRESS2, JOIN_DATE, MEMCODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,sysdate, ?)";
//
//	        try (Connection conn = getConnection();
//	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//	            pstmt.setString(1, dto.getUserid());
//	            pstmt.setString(2, dto.getNickname());
//	            pstmt.setString(3, dto.getEmail());
//	            pstmt.setString(4, dto.getGender());
//	            pstmt.setString(5, dto.getAgeRange());
//	            pstmt.setString(6, dto.getBirthday());
//	            pstmt.setString(7, dto.getConnectedAt());
//
//	            pstmt.executeUpdate();
//	        }
//	    }
}
