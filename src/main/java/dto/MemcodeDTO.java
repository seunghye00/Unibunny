package dto;

public class MemcodeDTO {
	private int memcode;
	private String grade;
	
	public MemcodeDTO(int memcode, String grade) {
		super();
		this.memcode = memcode;
		grade = grade;
	}
	
	public MemcodeDTO() {
		super();
	}

	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
}
