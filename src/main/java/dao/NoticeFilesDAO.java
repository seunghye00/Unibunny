package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import dto.NoticeFilesDTO;

public class NoticeFilesDAO {

    private static NoticeFilesDAO instance;

    public static synchronized NoticeFilesDAO getInstance() {
        if (instance == null) {
            instance = new NoticeFilesDAO();
        }
        return instance;
    }

    private NoticeFilesDAO() {}

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    public int insertFile(NoticeFilesDTO dto) throws Exception {
        String sql = "INSERT INTO notice_files VALUES (NTC_FILES_SEQ.NEXTVAL, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getOriname());
            ps.setString(2, dto.getSysname());
            ps.setInt(3, dto.getNotice_seq());
            return ps.executeUpdate();
        }
    }
}
