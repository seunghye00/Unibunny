package dto;

import java.sql.Timestamp;

public class FilesDTO {
	private int file_seq;
	private String oriname;
	private String sysname;
	private int board_seq;
	public FilesDTO(int file_seq, String oriname, String sysname, int board_seq) {
		super();
		this.file_seq = file_seq;
		this.oriname = oriname;
		this.sysname = sysname;
		this.board_seq = board_seq;
	}
	public FilesDTO() {
		super();
	}
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
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
