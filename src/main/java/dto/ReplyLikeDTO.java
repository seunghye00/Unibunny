package dto;

public class ReplyLikeDTO {
	private int reply_like_seq;
	private String userid;
	private int reply_seq;

	public int getReply_like_seq() {
		return reply_like_seq;
	}

	public void setReply_like_seq(int reply_like_seq) {
		this.reply_like_seq = reply_like_seq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getReply_seq() {
		return reply_seq;
	}

	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}

	public ReplyLikeDTO(int reply_like_seq, String userid, int reply_seq) {
		super();
		this.reply_like_seq = reply_like_seq;
		this.userid = userid;
		this.reply_seq = reply_seq;
	}

	public ReplyLikeDTO() {
		super();
	}
}
