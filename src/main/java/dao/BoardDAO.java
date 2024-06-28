package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	// 최근순으로 리스트 정렬
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

	// 최근순으로 게임별 정렬
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

	// 조회수 순으로 리스트 정렬
	public List<BoardDTO> selectListView(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by view_count desc) rown from board where delete_yn = 'N') where (rown between ? and ?)";
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

	// 조회수 순으로 게임별 정렬
	public List<BoardDTO> selectListViewGame(int startNum, int endNum, String game_num) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over(order by view_count desc) rown from board where delete_yn = 'N' and  game_id = ?) where rown between ? and ?";
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

	// 추천수 순으로 리스트 정렬
	public Map<BoardDTO, Integer> selectListLike(int startNum, int endNum) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "with ranked_board as (select b.*, coalesce(bl.like_count, 0) as like_count, row_number() over (order by coalesce(bl.like_count, 0) desc) as rown from board b left join (select board_seq, count(*) as like_count from board_like group by board_seq) bl on b.board_seq = bl.board_seq where b.delete_yn = 'N') select * from ranked_board where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			// hashpmap은 출력시 순서 보장이 안되게 되어있기 때문에 LinkedHashmap으로 값 받아오기
			Map<BoardDTO, Integer> resultList = new LinkedHashMap<>();
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
					int like_count = rs.getInt("like_count");
					BoardDTO dto = new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn,
							delete_date, game_id, nickname);
					resultList.put(dto, like_count);
				}
				return resultList;
			}
		}
	}

	// 추천수 순으로 게임별 정렬
	public Map<BoardDTO, Integer> selectListLikeGame(int startNum, int endNum, String game_num) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "with ranked_board as (select b.*, coalesce(bl.like_count, 0) as like_count, row_number() over (order by coalesce(bl.like_count, 0) desc) as rown from board b left join (select board_seq, count(*) as like_count from board_like group by board_seq) bl on b.board_seq = bl.board_seq where b.game_id = ? and b.delete_yn = 'N') select * from ranked_board where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			// hashpmap은 출력시 순서 보장이 안되게 되어있기 때문에 LinkedHashmap으로 값 받아오기
			Map<BoardDTO, Integer> resultList = new LinkedHashMap<>();
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
					int like_count = rs.getInt("like_count");
					BoardDTO dto = new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn,
							delete_date, game_id, nickname);
					resultList.put(dto, like_count);
				}
				return resultList;
			}
		}
	}

	// 검색어 순으로 게임별 정렬
	public List<BoardDTO> searchListView(int startNum, int endNum, String title_txt) throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over (order by board_seq desc) rown from board where delete_yn = 'N' and lower(title) like ?) where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, "%" + title_txt.toLowerCase() + "%");
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

	// 검색어 순으로 게임별 정렬
	public List<BoardDTO> searchListGameView(int startNum, int endNum, String game_num, String title_txt)
			throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		String sql = "select * from (select board.*, row_number() over (order by board_seq desc) rown from board where game_id = ? and delete_yn = 'N' and lower(title) like ?) where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, game_num);
			pstat.setString(2, "%" + title_txt.toLowerCase() + "%");
			pstat.setInt(3, startNum);
			pstat.setInt(4, endNum);
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
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
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, game_id);
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	// 검색 별 카운트 조회
	public int getRecordCountSearch(String game_id, String title_txt) throws Exception {
		String sql = "select count(*) from board where delete_yn = 'N' and game_id = ? and title like ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, game_id);
			pstat.setString(2, "%" + title_txt.toLowerCase() + "%");
			try (ResultSet rs = pstat.executeQuery();) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}

	public int searchBoardCount(String id) throws Exception {
		// 마이페이지에서 게시물 작성 수를 확인하는 메서드
//		해당 회원이 작성한 게시물 수를 반환

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

	public List<BoardDTO> searchMyBoardList(int startNum, int endNum, String id) throws Exception {
//		'해당하는 회원이' 작성한 글 리스트를 반환하는 메서드
//		회원의 id를 인자로 가져와서 닉네임을 찾는다.

		System.out.println("board_seq");
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) rown from board where nickname = (select nickname from member where userid = ?)) where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, id);
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;

			}
		}
	}

	public List<BoardDTO> searchMyCommentedBoardList(int startNum, int endNum, String id) throws Exception {
		// 해당 회원이 댓글을 작성한 게시물들의 리스트를 중복없이 반환함

		System.out.println("board_seq");
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) rown "
				+ "from board where board_seq in (select distinct board_seq from reply where nickname = (select nickname from member where userid = ?))) "
				+ "where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, id);
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
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

	// JSON 변환 메서드 추가
	public List<Map<String, Object>> convertToJSONReadyList(Map<BoardDTO, Integer> thumbList) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map.Entry<BoardDTO, Integer> entry : thumbList.entrySet()) {
			BoardDTO boardDTO = entry.getKey();
			int likeCount = entry.getValue();

			Map<String, Object> boardMap = boardDTO.toMap();
			boardMap.put("like_count", likeCount);

			resultList.add(boardMap);
		}
		return resultList;
	}

	public List<BoardDTO> searchMyBookmarkedBoardList(int startNum, int endNum, String id) throws Exception {
//		회원이 북마크한 게시글 리스트를 반환하는 메서드

		System.out.println("board_seq");
		String sql = "select * from (select board.*, row_number() over(order by board_seq desc) rown "
				+ "from board where board_seq in (select board_seq from bookmark where userid = ?)) "
				+ "where rown between ? and ?";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();
			pstat.setString(1, id);
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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
		}
	}

//	관리자가 deleteYN = Y인 삭제된게시물(임시 보관 게시물)을 조회하는 메서드
	public List<BoardDTO> searchDeletedList() throws Exception {
		// 내부 조인으로 desc 순으로 번호 출력
		System.out.println("board_seq");
		String sql = "select * from board where delete_YN = 'Y'";
		try (Connection con = this.getconnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			List<BoardDTO> list = new ArrayList<>();

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
					list.add(new BoardDTO(board_seq, title, content, write_date, view_count, delete_yn, delete_date,
							game_id, nickname));
				}
				return list;
			}
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
