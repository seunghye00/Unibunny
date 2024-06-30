package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GameDAO {

	private static GameDAO instance;
	
	public static synchronized GameDAO getInstance() {
		if (instance == null) {
			instance = new GameDAO();
		}
		return instance;
	}

	private GameDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
