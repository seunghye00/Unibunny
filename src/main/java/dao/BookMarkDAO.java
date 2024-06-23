package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookMarkDAO {
	
	private static BookMarkDAO instance;
	
	public static synchronized BookMarkDAO getInstance() {
		if (instance == null) {
			instance = new BookMarkDAO();
		}
		return instance;
	}

	private BookMarkDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	// board_seq 값으로 해당 글의 북마크 수를 조회하는 메서드
	public int selectByBoardSeq(int board_Seq) throws Exception {
		
		String sql = "select count(*) from bookmark where board_seq = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, board_Seq);
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
	}
}
