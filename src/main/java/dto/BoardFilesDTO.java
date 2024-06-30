package dto;

public class BoardFilesDTO {
	private int board_files_seq;
	private String oriname;
	private String sysname;
	private int board_seq;

	public BoardFilesDTO() {
		super();
	}

	public BoardFilesDTO(int board_files_seq, String oriname, String sysname, int board_seq) {
		super();
		this.board_files_seq = board_files_seq;
		this.oriname = oriname;
		this.sysname = sysname;
		this.board_seq = board_seq;
	}

	public int getFile_seq() {
		return board_files_seq;
	}

	public void setFile_seq(int file_seq) {
		this.board_files_seq = board_files_seq;
	}

	public String getOriname() {
		return oriname;
	}

	public void setOriname(String oriname) {
		this.oriname = oriname;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
}
