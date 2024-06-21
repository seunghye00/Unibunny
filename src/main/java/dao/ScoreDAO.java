package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScoreDAO {

	private static ScoreDAO instance;
	
	public static synchronized ScoreDAO getInstance() {
		if (instance == null) {
			instance = new ScoreDAO();
		}
		return instance;
	}

	private ScoreDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
