package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyLikeDAO {

	   private static ReplyLikeDAO instance;
	   
	   public static synchronized ReplyLikeDAO getInstance() {
	      if (instance == null) {
	         instance = new ReplyLikeDAO();
	      }
	      return instance;
	   }

	   private ReplyLikeDAO() {}

	   private Connection getConnection() throws Exception {
	      Context ctx = new InitialContext();
	      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
	      return ds.getConnection();
	   }
}
