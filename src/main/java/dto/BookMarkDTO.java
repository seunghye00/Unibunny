package dto;

public class BookMarkDTO {
	private int bookmark_seq;
	private String userid;
	private int board_seq;
	public BookMarkDTO(int bookmark_seq, String userid, int board_seq) {
		super();
		this.bookmark_seq = bookmark_seq;
		this.userid = userid;
		this.board_seq = board_seq;
	}
	public BookMarkDTO() {
		super();
	}
	public int getBookmark_seq() {
		return bookmark_seq;
	}
	public void setBookmark_seq(int bookmark_seq) {
		this.bookmark_seq = bookmark_seq;
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
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	
}
