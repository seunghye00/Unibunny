package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	private static BoardDAO instance;
	
	public synchronized static BoardDAO getInstance(){
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private Connection getconnection() throws Exception {
		Context ctx = new InitialContext(); 
		DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	private BoardDAO(){};
	
	public List<BoardDTO> selectBoardList(int startNum, int endNum) throws Exception {
		String sql = "select * from (select board.*, row_number() over(order by seq desc) rown from board) where rown between ? and ?";
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
					 int thumbs_up = rs.getInt("thumbs_up");
					 String delete_yn = rs.getString("delete_yn");
					 Timestamp delete_date = rs.getTimestamp("delete_date");
					 int game_id = rs.getInt("game_id");
					 int nickname = rs.getInt("nickname");
					 list.add(new BoardDTO(board_seq,title,content,write_date,view_count
							 ,thumbs_up,delete_yn,delete_date,game_id,nickname));
				 }
				 return list;
			}
		}
 	}
	
	
	// 마이페이지에서 게시물 작성 수를 확인하는 메서드
	public int searchBoardCount(String id) throws Exception {
	    String sql = "select count(*) from board where nickname = (select nickname from member where userid = ?)";
	    try (
	        Connection con = this.getconnection();  
	        PreparedStatement pstat = con.prepareStatement(sql);
	    ) {
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
	    return 0;  // 기본값으로 0을 반환
	}
	
	
	
	
	
	
}
