package dto;

public class NoticeFilesDTO {
    private int ntc_files_seq;
    private String oriname;
    private String sysname;
    private int notice_seq;

    public NoticeFilesDTO(int ntc_files_seq, String oriname, String sysname, int notice_seq) {
        super();
        this.ntc_files_seq = ntc_files_seq;
        this.oriname = oriname;
        this.sysname = sysname;
        this.notice_seq = notice_seq;
    }

    public NoticeFilesDTO() {
        super();
    }

    public int getNtc_files_seq() {
        return ntc_files_seq;
    }

    public void setNtc_files_seq(int ntc_files_seq) {
        this.ntc_files_seq = ntc_files_seq;
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

    public int getNotice_seq() {
        return notice_seq;
    }

    public void setNotice_seq(int notice_seq) {
        this.notice_seq = notice_seq;
    }
}
