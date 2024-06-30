package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.GameDTO;

public class GameDAO {

	private static GameDAO instance;
	
	public static synchronized GameDAO getInstance() {
		if (instance == null) {
			instance = new GameDAO();
		}
		return instance;
	}

	public List<GameDTO> getAllGames() {
        List<GameDTO> games = new ArrayList<>();
        String sql = "SELECT game_id, game_name FROM game";

        try (Connection con = this.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {

            while (rs.next()) {
                GameDTO game = new GameDTO();
                game.setGame_id(rs.getInt("game_id"));
                game.setGame_name(rs.getString("game_name"));;
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }
	
	
	
	
	
	private GameDAO() {}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
