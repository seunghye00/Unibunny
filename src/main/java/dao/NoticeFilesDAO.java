package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getOriname());
            ps.setString(2, dto.getSysname());
            ps.setInt(3, dto.getNotice_seq());
            System.out.println("파일 삽입: " + dto.getOriname() + ", " + dto.getSysname() + ", " + dto.getNotice_seq());
            return ps.executeUpdate();
        }
    }

    public List<NoticeFilesDTO> getFilesByNoticeSeq(int noticeSeq) throws Exception {
        String sql = "SELECT * FROM notice_files WHERE notice_seq = ?";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeSeq);
            try (ResultSet rs = ps.executeQuery()) {
                List<NoticeFilesDTO> list = new ArrayList<>();
                while (rs.next()) {
                    NoticeFilesDTO dto = new NoticeFilesDTO();
                    dto.setNtc_files_seq(rs.getInt("ntc_files_seq"));
                    dto.setNotice_seq(rs.getInt("notice_seq"));
                    dto.setOriname(rs.getString("oriname"));
                    dto.setSysname(rs.getString("sysname"));
                    list.add(dto);
                }
                return list;
            }
        }
    }
    
    public int deleteFileById(int fileSeq) throws Exception {
        String sql = "DELETE FROM notice_files WHERE ntc_files_seq = ?";
        try (Connection con = this.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fileSeq);
            return ps.executeUpdate();
        }
    }

    public int updateFile(NoticeFilesDTO dto) throws Exception {
        String sql = "UPDATE notice_files SET oriname = ?, sysname = ? WHERE notice_seq = ?";
        try (Connection con = this.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getOriname());
            ps.setString(2, dto.getSysname());
            ps.setInt(3, dto.getNtc_files_seq());
            return ps.executeUpdate();
        }
    }

//	
    public NoticeFilesDTO getFile(int fileSeq) throws Exception {
        String sql = "SELECT * FROM notice_files WHERE ntc_files_seq = ?";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fileSeq);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NoticeFilesDTO dto = new NoticeFilesDTO();
                    dto.setNtc_files_seq(rs.getInt("ntc_files_seq"));
                    dto.setOriname(rs.getString("oriname"));
                    dto.setSysname(rs.getString("sysname"));
                    dto.setNotice_seq(rs.getInt("notice_seq"));
                    return dto;
                } else {
                    return null;
                }
            }
        }
    }
}
