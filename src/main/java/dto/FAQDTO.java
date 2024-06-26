package dto;

public class FAQDTO {
	private int faq_seq;
	private String title;
	private String content;
	
	public FAQDTO(int faq_seq, String title, String content) {
		super();
		this.faq_seq = faq_seq;
		this.title = title;
		this.content = content;
	}
	
	public FAQDTO() {
		super();
	}
	
	public int getFaq_seq() {
		return faq_seq;
	}
	public void setFaq_seq(int faq_seq) {
		this.faq_seq = faq_seq;
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
	
}
