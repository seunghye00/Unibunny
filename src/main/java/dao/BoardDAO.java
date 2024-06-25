package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	private static BoardDAO instance;

	public synchronized static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	private Connection getconnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	private BoardDAO() {
	};

	// 게시판 게시글 조회
	public List<BoardDTO> selectListAll(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) as rown from board where delete_yn = 'N') where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn,
							delete_date, game_id, nickname));
				}
				return list;
			}
		}
	}
	public List<BoardDTO> selectListAllGame(int startNum, int endNum, String game_num) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) rown from board where delete_yn = 'N' and game_id = ?) where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, game_num);
			pstat.setInt(2, startNum);
			pstat.setInt(3, endNum);
			try (ResultSet rs = pstat.executeQuery();) {
				while (rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn,
							delete_date, game_id, nickname));
				}
				return list;
			}
		}
 	}
	public List<BoardDTO> selectListView(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by view_count desc) rown from board where delete_yn = 'N') where (rown between ? and ?)";
		try (
				Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);	
				) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try (	
					ResultSet rs= pstat.executeQuery();
					){
				while(rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq,title,content,write_date,view_count
							,delete_yn,delete_date,game_id,nickname));
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO> selectListViewGame(int startNum, int endNum, String game_num) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by view_count desc) rown from board where delete_yn = 'N' and  game_id = ?) where rown between ? and ?";
		try (
				Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);	
				) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, game_num);
			pstat.setInt(2, startNum);
			pstat.setInt(3, endNum);
			try (	
					ResultSet rs= pstat.executeQuery();
					){
				while(rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq,title,content,write_date,view_count
							,delete_yn,delete_date,game_id,nickname));
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO> selectListLike(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "";
		try (
				Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);	
				) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try (	
					ResultSet rs= pstat.executeQuery();
					){
				while(rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq,title,content,write_date,view_count
							,delete_yn,delete_date,game_id,nickname));
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO> selectListLikeGame(int startNum, int endNum, String game_num) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by thumbs_up desc) rown from board) where (rown between ? and ?) and game_id = ? and delete_yn = 'N'";
		try (
				Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);	
				) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			pstat.setString(3, game_num);
			try (	
					ResultSet rs= pstat.executeQuery();
					){
				while(rs.next()) {
					int board_seq = rs.getInt("board_seq");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					String delete_yn = rs.getString("delete_yn");
					Timestamp delete_date = rs.getTimestamp("delete_date");
					int game_id = rs.getInt("game_id");
					String nickname = rs.getString("nickname");
					list.add(new BoardDTO(board_seq,title,content,write_date,view_count
							,delete_yn,delete_date,game_id,nickname));
				}
				return list;
			}
		}
	}
	
	// 전체 게시글 카운트 조회 
	public int getRecordCount() throws Exception {
		String sql = "select count(*) from board where delete_yn = 'N'";
		try (Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
	}
	
	// 게임 별 카운트 조회 
	public int getRecordCountGame(String game_id) throws Exception {
		String sql = "select count(*) from board where delete_yn = 'N' and game_id = ?";
		try (Connection con = this.getconnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				) {
			pstat.setString(1, game_id);
			try (	
					ResultSet rs= pstat.executeQuery();
					){
				rs.next();
				return rs.getInt(1);
			}
		}
	}
	// 마이페이지에서 게시물 작성 수를 확인하는 메서드
	public int searchBoardCount(String id) throws Exception {
		String sql = "select count(*) from board where nickname = (select nickname from member where userid = ?)";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, id);

			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("게시물 작성 수 조회 중 오류 발생", e);
		}
		return 0; // 기본값으로 0을 반환
	}

	// board_seq 값으로 해당 레코드를 반환하는 메서드
	public BoardDTO selectBySeq(int seq) throws Exception {

		String sql = "select * from board where board_seq = ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);
			BoardDTO dto = new BoardDTO();
			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					dto.setBoard_seq(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setContent(rs.getString(3));
					dto.setWrite_date(rs.getTimestamp(4));
					dto.setView_count(rs.getInt(5));
					dto.setGame_id(rs.getInt(8));
					dto.setNickname(rs.getString(9));
				}
				return dto;
			}
		}
	}

	// 게시글 삭제 메서드
	public int deleteBySeq(int board_seq) throws Exception {
		String sql = "delete from board where board_seq = ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, board_seq);
			return pstat.executeUpdate();
		}
	}

	// 게시글 수정 메서드
	public int update(int board_seq, String title, String content) throws Exception {
		String sql = "update board set title = ?, content = ? where board_seq = ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, title);
			pstat.setString(2, content);
			pstat.setInt(3, board_seq);
			return pstat.executeUpdate();
		}
	}

	
//	// 더미데이터만들기
//		public static void main(String[] args) throws Exception {
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String id = "test0607";
//			String pw = "test0607";
//
//			// SQL 문: 더미 데이터를 삽입하는 SQL 문
//			String sql = "INSERT INTO Board (notice_seq, TITLE, CONTENT, WRITE_DATE, VIEW_COUNT, NICKNAME) "
//					+ "VALUES (notice_seq.NEXTVAL, ?, ?, SYSDATE, ?, 'admin')";
//
//			try (Connection con = DriverManager.getConnection(url, id, pw);
//					PreparedStatement pstat = con.prepareStatement(sql)) {
//				for (int i = 1; i <= 50; i++) {
//					pstat.setString(1, "Title " + i); // TITLE
//					pstat.setString(2, "Content " + i); // CONTENT
//					pstat.setInt(3, (int) (Math.random() * 100)); // VIEW_COUNT
//					pstat.setInt(4, (int) (Math.random() * 50)); // THUMBS_UP
//					pstat.setInt(5, (int) (Math.random() * 10) + 1); // GAME_ID
//					pstat.setString(6, "User" + i); // NICKNAME
//					pstat.addBatch();
//				}
//				pstat.executeBatch();
//				System.out.println("50개의 더미 데이터가 성공적으로 삽입되었습니다.");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
}
