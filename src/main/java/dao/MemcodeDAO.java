package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemcodeDAO {

	private static MemcodeDAO instance;
	
	public static synchronized MemcodeDAO getInstance() {
		if (instance == null) {
			instance = new MemcodeDAO();
		}
		return instance;
	}

	private MemcodeDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
