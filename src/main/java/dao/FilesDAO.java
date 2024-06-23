package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FilesDTO;

public class FilesDAO {

	private static FilesDAO instance;
	
	public static synchronized FilesDAO getInstance() {
		if (instance == null) {
			instance = new FilesDAO();
		}
		return instance;
	}

	private FilesDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
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
	
	
	
}
