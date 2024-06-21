package dto;

import java.sql.Timestamp;

public class ScoreDTO {
	private int score_seq;
	private String title;
	private int game_id;
	private String nickname;
	private	Timestamp end_time;
	private	int log_seq;
	public ScoreDTO(int score_seq, String title, int game_id, String nickname, Timestamp end_time, int log_seq) {
		super();
		this.score_seq = score_seq;
		this.title = title;
		this.game_id = game_id;
		this.nickname = nickname;
		this.end_time = end_time;
		this.log_seq = log_seq;
	}
	
	public ScoreDTO() {
		super();
	}
	
	public int getScore_seq() {
		return score_seq;
	}
	public void setScore_seq(int score_seq) {
		this.score_seq = score_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public int getLog_seq() {
		return log_seq;
	}
	public void setLog_seq(int log_seq) {
		this.log_seq = log_seq;
	}
	
}
