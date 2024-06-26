package dto;

import java.sql.Timestamp;

public class QNADTO {
	private int question_seq;
	private String question_title;
	private String question_content;
	private	Timestamp write_date;
	private String answer_yn;
	private String answer_content;
	private	Timestamp answer_date;
	private	String userid;
	
	
	public QNADTO(int question_seq, String question_title, String question_content, Timestamp write_date,
			String answer_yn, String answer_content, Timestamp answer_date, String userid) {
		super();
		this.question_seq = question_seq;
		this.question_title = question_title;
		this.question_content = question_content;
		this.write_date = write_date;
		this.answer_yn = answer_yn;
		this.answer_content = answer_content;
		this.answer_date = answer_date;
		this.userid = userid;
	}
	public QNADTO() {
		super();
	}
	public int getQuestion_seq() {
		return question_seq;
	}
	public void setQuestion_seq(int question_seq) {
		this.question_seq = question_seq;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public String getAnswer_yn() {
		return answer_yn;
	}
	public void setAnswer_yn(String answer_yn) {
		this.answer_yn = answer_yn;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public Timestamp getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Timestamp answer_date) {
		this.answer_date = answer_date;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}