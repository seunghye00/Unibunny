package dao;

import java.sql.Connection;

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
}
