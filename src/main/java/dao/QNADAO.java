package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QNADAO {

	private static QNADAO instance;
	
	public static synchronized QNADAO getInstance() {
		if (instance == null) {
			instance = new QNADAO();
		}
		return instance;
	}

	private QNADAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
