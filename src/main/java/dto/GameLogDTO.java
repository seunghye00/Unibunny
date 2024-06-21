package dto;

import java.sql.Timestamp;

public class GameLogDTO {
	private int log_seq;
	private	Timestamp start_time;
	private int game_id;
	private	String nickname;
	public GameLogDTO(int log_seq, Timestamp start_time, int game_id, String nickname) {
		super();
		this.log_seq = log_seq;
		this.start_time = start_time;
		this.game_id = game_id;
		this.nickname = nickname;
	}
	public GameLogDTO() {
		super();
	}
	public int getLog_seq() {
		return log_seq;
	}
	public void setLog_seq(int log_seq) {
		this.log_seq = log_seq;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
