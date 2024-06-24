package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardLikeDAO {

	   private static BoardLikeDAO instance;
	   
	   public static synchronized BoardLikeDAO getInstance() {
	      if (instance == null) {
	         instance = new BoardLikeDAO();
	      }
	      return instance;
	   }

	   private BoardLikeDAO() {}

	   private Connection getConnection() throws Exception {
	      Context ctx = new InitialContext();
	      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
	      return ds.getConnection();
	   }
}
