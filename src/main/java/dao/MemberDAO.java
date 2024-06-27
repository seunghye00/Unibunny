package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    

	public MemberDTO searchProfileInfo(String id) throws Exception {
        // 마이페이지 프로필 정보 조회(해당하는 아이디의 닉네임, 가입날짜 등)
        String sql = "select * from member where userid = ?";

        try (
            Connection con = this.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql);
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

                    MemberDTO dto = new MemberDTO(userid, nickname, pw, phone, reg_num, email, postcode, address1, address2, join_date, memcode, profile_img);
                    return dto;
                }

            }
        }
        return null;
    }

    // 가입날짜를 원하는 형식으로 변환하여 출력하는 메서드
    public String getFormattedJoinDate(MemberDTO dto) {
        Timestamp join_date = dto.getJoin_date();
        return formatTimestamp(join_date);
    }

    // Timestamp를 원하는 형식의 문자열로 변환하는 메서드
    private String formatTimestamp(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date date = new Date(timestamp.getTime());
        return sdf.format(date);
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
	

	public void updateProfileImage(String userid, String sysName) throws Exception {
//		회원이 선택한 프로필 이미지 정보를 DB의 회원 테이블에 저장함
		
		String sql = "UPDATE member SET profile_img = ? WHERE userid = ?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, "/image/mypage_image/" + sysName);
            pstat.setString(2, userid);
            pstat.executeUpdate();
		}
	}
	// 아이디 찾기
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
	

	// 계정 정보를 간략히 Map 형식으로 가져옴.
	public Map<String, String> getAccount(String userid) throws Exception {

		// Map 초기화
		Map<String, String> map = new HashMap<String, String>();

		// userid로 닉네임, 프로필이미지, 멤버코드 가져오는 쿼리
		String sql = "select userid, nickname, profile_img, MEMCODE from member where userid=?";
		try (Connection con = this.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, userid);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {

					// Map에 Key, Value 형식으로 값을 삽입.
					map.put("userid", rs.getString("USERID"));
					map.put("nickname", rs.getString("NICKNAME"));
					map.put("profile_img", rs.getString("PROFILE_IMG"));
					map.put("memcode", rs.getString("MEMCODE"));
				}
				// map 변수 반환.
				return map;
			}
		}
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

	// 회원 or 블랙리스트 목록 중 범위 내의 관리자 계정을 제외한 테이터 중 id, 닉네임, 가입 날짜를 반환하는 메서드
	public List<MemberDTO> selectNtoM(int start_num, int end_num, String grade) throws Exception {

		String sql = "select userid, nickname, join_date from (select m.userid, m.nickname, m.join_date,rownum rn from member m join memcode mc on m.memcode = mc.memcode where mc.grade = ?) where rn between ? and ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, grade);
			pstat.setInt(2, start_num);
			pstat.setInt(3, end_num);
			try (ResultSet rs = pstat.executeQuery();) {
				List<MemberDTO> list = new ArrayList<>();
				while (rs.next()) {
					String userid = rs.getString(1);
					String nickname = rs.getString(2);
					Timestamp join_date = rs.getTimestamp(3);
					list.add(new MemberDTO(userid, nickname, join_date));
				}
				return list;
			}
		}
	}

	// 일반 회원 or 블랙리스트 목록의 전체 데이터 갯수를 반환하는 메서드
	public int selectAll(String grade) throws Exception {

		String sql = "select count(*) from member where memcode = (select memcode from memcode where grade = ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, grade);
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}

//	   public void kakaoLogin(MemberDTO dto) throws SQLException {
//	        String sql = "INSERT INTO member (USERID, NICKNAME, PW, PHONE, REG_NUM, EMAIL, POSTCODE, ADDRESS1, ADDRESS2, JOIN_DATE, MEMCODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,sysdate, ?)";
//
//           try (Connection conn = getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//               pstmt.setString(1, dto.getUserid());
//               pstmt.setString(2, dto.getNickname());
//               pstmt.setString(3, dto.getEmail());
//               pstmt.setString(4, dto.getGender());
//               pstmt.setString(5, dto.getAgeRange());
//               pstmt.setString(6, dto.getBirthday());
//               pstmt.setString(7, dto.getConnectedAt());
//
//               pstmt.executeUpdate();
//           }
//       }
	

				
	
	
	
}
