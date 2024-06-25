package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

	// 회원가입
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

	// 로그인
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

	// 회원가입 정규표현식
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

		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, reg_num);
			pst.setString(2, email);
			pst.setString(3, phone);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getString("USERID");
				} else {
					return "";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	// 비밀번호 찾기
	public boolean findPassword(String userid, String newPassword, String email, String reg_num) throws Exception {
		String sql = "SELECT reg_num FROM member WHERE userid = ? AND email = ?";
		String regNum = reg_num;
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, userid);
			pst.setString(2, email);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String db_value = rs.getString("REG_NUM");
					if (regNum.indexOf("-") != -1) {
						regNum = regNum.substring(regNum.indexOf("-"));
					}
					String substr_dbVal = db_value.substring(0, db_value.indexOf("-"));
					if (substr_dbVal.equals(regNum)) {
						regNum = db_value;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		sql = "UPDATE member SET pw = ? WHERE userid = ? AND email = ? AND reg_num = ?";

		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
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

	public MemberDTO searchProfileInfo(String id) throws Exception {
		// 마이페이지 프로필 정보 조회(해당하는 아이디의 닉네임,가입날짜 등)

		String sql = "select * from member where userid = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);

		) {
			pstat.setString(1, id);

			try (ResultSet rs = pstat.executeQuery();) {

				while (rs.next()) {

					String userid = rs.getString("userid");
					String nickname = rs.getString("nickname");
					String pw = rs.getString("pw");
					String phone = rs.getString("phone");
					String reg_num = rs.getString("reg_num");
					String email = rs.getString("email");
					String postcode = rs.getString("postcode");
					String address1 = rs.getString("address1");
					String address2 = rs.getString("address2");
					Timestamp join_date = rs.getTimestamp("join_date");
					int memcode = rs.getInt("memcode");
					String profile_img = rs.getString("profile_img");

					MemberDTO dto = new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode, address1,
							address2, join_date, memcode, profile_img);
					return dto;
				}

			}
		}
		return null;
	}

	// 세션에 저장된 로그인 ID로 회원의 닉네임을 조회하는 메서드
	public String getNickname(String id) throws Exception {

		String sql = "select nickname from member where userid = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setObject(1, id);
			try (ResultSet rs = pstat.executeQuery();) {
				// 수정할 코드
				if (rs.next()) {
					return rs.getString("nickname");
				}
				// 임시 데이터 리턴
				return "test_user";
			}
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
