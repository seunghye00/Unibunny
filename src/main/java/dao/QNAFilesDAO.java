package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import dto.QNAFilesDTO;

public class QNAFilesDAO {

    private static QNAFilesDAO instance;

    public static synchronized QNAFilesDAO getInstance() {
        if (instance == null) {
            instance = new QNAFilesDAO();
        }
        return instance;
    }

    private QNAFilesDAO() {}

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        return ds.getConnection();
    }

    public int insertFile(QNAFilesDTO dto) throws Exception {
        String sql = "INSERT INTO qna_files VALUES (QNA_FILES_SEQ.NEXTVAL, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getOriname());
            ps.setString(2, dto.getSysname());
            ps.setInt(3, dto.getQuestion_seq());
            return ps.executeUpdate();
        }
    }
}
