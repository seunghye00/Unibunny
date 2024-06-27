package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.NoticeDTO;

public class NoticeDAO {

	private static NoticeDAO instance;

	public static synchronized NoticeDAO getInstance() {
		if (instance == null) {
			instance = new NoticeDAO();
		}
		return instance;
	}

	private NoticeDAO() {
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	// 게시판 게시글 조회
	public List<NoticeDTO> selectListAll(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select notice.*, row_number() over(order by notice_seq desc) rown from notice) where rown between ? and ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<NoticeDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int notice_seq = rs.getInt("notice_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String nickname = rs.getString("nickname");
					list.add(new NoticeDTO(notice_seq, title, content, write_date, view_count, nickname));
				}
				return list;
			}
		}
	}

	// 전체 게시글 카운트 조회
	public int getRecordCount() throws Exception {
		String sql = "select count(*) from notice";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
	}

	// 관리자 공지사항 게시물 등록
	public int insertNotice(NoticeDTO dto) throws Exception {
		String sql = "INSERT INTO notice (NOTICE_SEQ, TITLE, CONTENT, WRITE_DATE, VIEW_COUNT, NICKNAME) VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
		try (Connection con = this.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getNickname());
			return ps.executeUpdate();
		}
	}

	// 시퀸스 가져오기
	public int getLastInsertedId() throws Exception {
		String sql = "SELECT NOTICE_SEQ.CURRVAL FROM dual";
		try (Connection con = this.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		}
	}

	// 공지사항 상세 조회
	public NoticeDTO getNoticeById(int noticeSeq) throws Exception {
		NoticeDTO notice = null;
		String query = "SELECT * FROM notice WHERE notice_seq = ?";

		try (Connection conn = this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, noticeSeq);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					notice = new NoticeDTO();
					notice.setNotice_seq(rs.getInt("notice_seq"));
					notice.setTitle(rs.getString("title"));
					notice.setContent(rs.getString("content"));
					notice.setNickname(rs.getString("nickname"));
					notice.setWrite_date(rs.getTimestamp("write_date"));
					notice.setView_count(rs.getInt("view_count"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return notice;
	}
	
    public int deleteNotice(int noticeSeq) throws Exception {
        String sql = "DELETE FROM notice WHERE notice_seq = ?";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeSeq);
            return ps.executeUpdate();
        }
    }
    
    public int updateNotice(NoticeDTO dto) throws Exception {
        String sql = "UPDATE notice SET title = ?, content = ? WHERE notice_seq = ?";
        try (Connection con = this.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setInt(3, dto.getNotice_seq());
            return ps.executeUpdate();
        }
    }
    
    public int increaseViewCount(int noticeSeq) throws Exception {
        String sql = "UPDATE notice SET view_count = view_count + 1 WHERE notice_seq = ?";
        try (Connection con = this.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeSeq);
            return ps.executeUpdate();
        }
    }

}

// 더미데이터 생성
//	public static void main(String[] args) {
//	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	    String id = "bunny";
//	    String pw = "bunny";
//
//	    // SQL 문: 더미 데이터를 삽입하는 SQL 문
//	    String sql = "INSERT INTO notice (NOTICE_SEQ, TITLE, CONTENT, WRITE_DATE, VIEW_COUNT, NICKNAME) "
//	                + "VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, 'admin')";
//
//	    try (Connection con = DriverManager.getConnection(url, id, pw);
//	         PreparedStatement pstat = con.prepareStatement(sql)) {
//
//        for (int i = 1; i <= 50; i++) {
//	        for (int i = 1; i <= 10; i++) {
//	            pstat.setString(1, "Title " + i); // TITLE
//	            pstat.setString(2, "Content " + i); // CONTENT
//	            pstat.setInt(3, (int) (Math.random() * 100)); // VIEW_COUNT
//	            pstat.executeUpdate(); // 각각의 레코드를 삽입
//	        }
//
//	        System.out.println("50개의 더미 데이터가 성공적으로 삽입되었습니다.");
//
//	    } catch (Exception e) {
//	        e.printStackTrace(); // 예외 발생 시 콘솔에 출력
//	    }
//	}
