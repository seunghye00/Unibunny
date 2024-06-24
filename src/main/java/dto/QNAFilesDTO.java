package dto;

public class QNAFilesDTO {
    private int qna_file_SEQ;
    private String oriname;
    private String sysname;
    private int question_seq;

    public QNAFilesDTO(int qna_file_SEQ, String oriname, String sysname, int question_seq) {
        super();
        this.qna_file_SEQ = qna_file_SEQ;
        this.oriname = oriname;
        this.sysname = sysname;
        this.question_seq = question_seq;
    }

    public QNAFilesDTO() {
        super();
    }

    public int getQna_file_SEQ() {
        return qna_file_SEQ;
    }

    public void setQna_file_SEQ(int qna_file_SEQ) {
        this.qna_file_SEQ = qna_file_SEQ;
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

    public int getQuestion_seq() {
        return question_seq;
    }

    public void setQuestion_seq(int question_seq) {
        this.question_seq = question_seq;
    }
}
