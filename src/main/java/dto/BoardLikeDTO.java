package dto;

public class BoardLikeDTO {
	private int board_like_seq;
	private String userid;
	private int board_seq;

	public int getBoard_like_seq() {
		return board_like_seq;
	}

	public void setBoard_like_seq(int board_like_seq) {
		this.board_like_seq = board_like_seq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int reply_seq) {
		this.board_seq = reply_seq;
	}

	public BoardLikeDTO() {
		super();
	}

	public BoardLikeDTO(int board_like_seq, String userid, int board_seq) {
		super();
		this.board_like_seq = board_like_seq;
		this.userid = userid;
		this.board_seq = board_seq;
	}
}
