package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	private NoticeDAO() {}

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
					ResultSet rs = pstat.executeQuery();) 
			{
				rs.next();
				return rs.getInt(1);
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
}
