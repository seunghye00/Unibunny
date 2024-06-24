package dto;

import java.sql.Timestamp;

public class ReplyDTO {
	private int reply_seq;
	private String nickname;
	private String content;
	private Timestamp write_date;
	private int board_seq;
	private String delete_yn;
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
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
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public ReplyDTO(int reply_seq, String nickname, String content, Timestamp write_date, int board_seq, String delete_yn) {
		super();
		this.reply_seq = reply_seq;
		this.nickname = nickname;
		this.content = content;
		this.write_date = write_date;
		this.board_seq = board_seq;
		this.delete_yn = delete_yn;
	}
	public ReplyDTO() {
		super();
	}
}
