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
	
    public int insertFile(FilesDTO dto) throws Exception {
        String sql = "INSERT INTO FILES (FILE_SEQ, ORINAME, SYSNAME, BOARD_SEQ) VALUES (FILES_SEQ.NEXTVAL, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getOriname());
            ps.setString(2, dto.getSysname());
            ps.setInt(3, dto.getBoard_seq());
            return ps.executeUpdate();
        }
    }
}
