package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FAQDAO {
	
	private static FAQDAO instance;
	
	public static synchronized FAQDAO getInstance() {
		if (instance == null) {
			instance = new FAQDAO();
		}
		return instance;
	}

	private FAQDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
