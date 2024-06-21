package dto;

import java.sql.Timestamp;

public class QNADTO {
	private int question_seq;
	private String question;
	private	Timestamp write_date;
	private String answer_yn;
	private int answer_count;
	private	Timestamp answer_date;
	private	String id;
	public QNADTO(int question_seq, String question, Timestamp write_date, String answer_yn, int answer_count,
			Timestamp answer_date, String id) {
		super();
		this.question_seq = question_seq;
		this.question = question;
		this.write_date = write_date;
		this.answer_yn = answer_yn;
		this.answer_count = answer_count;
		this.answer_date = answer_date;
		this.id = id;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	public Timestamp getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Timestamp answer_date) {
		this.answer_date = answer_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
