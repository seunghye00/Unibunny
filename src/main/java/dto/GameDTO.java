package dto;

public class GameDTO {

	private int game_id;
	private String game_name;
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public GameDTO(int game_id, String game_name) {
		super();
		this.game_id = game_id;
		this.game_name = game_name;
	}
	
	public GameDTO() {
		
	}
	
}
