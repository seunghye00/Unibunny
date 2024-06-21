package dto;

import java.sql.Timestamp;

public class ReplyDTO {
	private int reply_seq;
	private String nickname;
	private String content;
	private Timestamp write_date;
	private int thumbs_up;
	private int board_seq;
	public int getReply_seq() {
		return reply_seq;
	}
	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public int getThumbs_up() {
		return thumbs_up;
	}
	public void setThumbs_up(int thumbs_up) {
		this.thumbs_up = thumbs_up;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public ReplyDTO(int reply_seq, String nickname, String content, Timestamp write_date, int thumbs_up,
			int board_seq) {
		super();
		this.reply_seq = reply_seq;
		this.nickname = nickname;
		this.content = content;
		this.write_date = write_date;
		
		this.thumbs_up = thumbs_up;
		this.board_seq = board_seq;
	}
	public ReplyDTO() {
		super();
	}
}
