package dto;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BoardDTO {
	private int board_seq;
	private String title;
	private String content;
	private Timestamp write_date;
	private int view_count;
	private String delete_yn;
	private Timestamp delete_date;
	private int game_id;
	private String nickname;
	
	public BoardDTO(int board_seq, String title, String content, Timestamp write_date, int view_count, String delete_yn, Timestamp delete_date, int game_id, String  nickname) {
		super();
		this.board_seq = board_seq;
		this.title = title;
		this.content = content;
		this.write_date = write_date;
		this.view_count = view_count;
		this.delete_yn = delete_yn;
		this.delete_date = delete_date;
		this.game_id = game_id;
		this.nickname = nickname;
	}
	public BoardDTO() {
		super();
	}
	
	public int getBoard_seq() {
		return board_seq;
	}
	
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getWrite_date() {
		return write_date;
	}
	
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	public int getView_count() {
		return view_count;
	}
	
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	public String getDelete_yn() {
		return delete_yn;
	}
	
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	
	public Timestamp getDelete_date() {
		return delete_date;
	}
	
	public void setDelete_date(Timestamp delete_date) {
		this.delete_date = delete_date;
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
	// BoardDTO에 JSON 변환을 위한 메서드 추가
	public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("board_seq", board_seq);
        map.put("title", title);
        map.put("content", content);
        map.put("write_date", write_date);
        map.put("view_count", view_count);
        map.put("delete_yn", delete_yn);
        map.put("delete_date", delete_date);
        map.put("game_id", game_id);
        map.put("nickname", nickname);
        return map;
    }
}
