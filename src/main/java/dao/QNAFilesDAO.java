package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    // 파일 등록!
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
    
    // 파일 조회!
    // 해당하는 게시물에 등록된 파일 찾기!
    public QNAFilesDTO selectFileByQuestionSeq(int question_seq) throws Exception {
        String sql = "SELECT * FROM qna_files WHERE question_seq = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, question_seq);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    QNAFilesDTO fileDto = new QNAFilesDTO();
                    fileDto.setQna_file_SEQ(rs.getInt("qna_file_seq"));
                    fileDto.setOriname(rs.getString("oriname"));
                    fileDto.setSysname(rs.getString("sysname"));
                    fileDto.setQuestion_seq(rs.getInt("question_seq"));
                    return fileDto;
                }
                return null;
            }
        }
    }
}
