package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonElement;

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

	// board_seq로 해당 게시물의 파일 목록 조회
	public List<FilesDTO> selectByBoardSeq(int board_seq) throws Exception {
		
		String sql = "select * from files where board_seq = ?";

		List<FilesDTO> list = new ArrayList<>();
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int file_seq = rs.getInt(1);
					String oriname = rs.getString(2);
					String sysname = rs.getString(3);
					list.add(new FilesDTO(file_seq, oriname, sysname, board_seq));
				}
				return list;
			}
		}
	}
}
