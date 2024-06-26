package dto;

import java.sql.Timestamp;

public class MemberDTO {
   private String userid;
   private String nickname;
   private String pw;
   private String phone;
   private String reg_num;
   private String email;
   private String postcode;
   private String address1;
   private String address2;
   private Timestamp join_date;
   private int memcode;
   private String profile_img;
   public MemberDTO(String userid, String nickname, String pw, String phone, String reg_num, String email,
         String postcode, String address1, String address2, Timestamp join_date, int memcode, String profile_img) {
      super();
      this.userid = userid;
      this.nickname = nickname;
      this.pw = pw;
      this.phone = phone;
      this.reg_num = reg_num;
      this.email = email;
      this.postcode = postcode;
      this.address1 = address1;
      this.address2 = address2;
      this.join_date = join_date;
      this.memcode = memcode;
      this.profile_img = profile_img;
   }
   public MemberDTO(String userid, String nickname, Timestamp join_date) {
      super();
      this.userid = userid;
      this.nickname = nickname;
      this.join_date = join_date;
   }
   public MemberDTO() {
      super();
   }
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public String getNickname() {
      return nickname;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getReg_num() {
      return reg_num;
   }
   public void setReg_num(String reg_num) {
      this.reg_num = reg_num;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPostcode() {
      return postcode;
   }
   public void setPostcode(String postcode) {
      this.postcode = postcode;
   }
   public String getAddress1() {
      return address1;
   }
   public void setAddress1(String address1) {
      this.address1 = address1;
   }
   public String getAddress2() {
      return address2;
   }
   public void setAddress2(String address2) {
      this.address2 = address2;
   }
   public Timestamp getJoin_date() {
      return join_date;
   }
   public void setJoin_date(Timestamp join_date) {
      this.join_date = join_date;
   }
   public int getMemcode() {
      return memcode;
   }
   public void setMemcode(int memcode) {
      this.memcode = memcode;
   }
   public String getProfile_img() {
      return profile_img;
   }
   public void setProfile_img(String profile_img) {
      this.profile_img = profile_img;
   }
   
}
