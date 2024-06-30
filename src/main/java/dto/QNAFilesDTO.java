package dto;

public class QNAFilesDTO {
    private int qna_files_SEQ;
    private String oriname;
    private String sysname;
    private int question_seq;

    public QNAFilesDTO(int qna_files_SEQ, String oriname, String sysname, int question_seq) {
        super();
        this.qna_files_SEQ = qna_files_SEQ;
        this.oriname = oriname;
        this.sysname = sysname;
        this.question_seq = question_seq;
    }

    public QNAFilesDTO() {
        super();
    }

    public int getQna_files_SEQ() {
        return qna_files_SEQ;
    }

    public void setQna_files_SEQ(int qna_files_SEQ) {
        this.qna_files_SEQ = qna_files_SEQ;
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
