package dto;

import java.sql.Timestamp;

public class NoticeDTO {
    private int notice_seq;
    private String title;
    private String content;
    private Timestamp write_date;
    private int view_count;
    private String nickname;

    public NoticeDTO(int notice_seq, String title, String content, Timestamp write_date, int view_count, String nickname) {
        super();
        this.notice_seq = notice_seq;
        this.title = title;
        this.content = content;
        this.write_date = write_date;
        this.view_count = view_count;
        this.nickname = nickname;
    }

    public NoticeDTO() {
        super();
    }

    public int getNotice_seq() {
        return notice_seq;
    }

    public void setNotice_seq(int notice_seq) {
        this.notice_seq = notice_seq;
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

    public Timestamp getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Timestamp write_date) {
        this.write_date = write_date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
