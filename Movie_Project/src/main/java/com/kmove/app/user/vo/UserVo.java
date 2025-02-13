package com.kmove.app.user.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserVo {
     	
		/**
		 *  Chaebun을 활용한 Primary Key ex) U 20001203 001
		 */	
	 	private String uidx ;
		
	 	
	 	
	 	// 유저의 이름 ex) 홍길동 
	 	private String uname;
	 	
	 	// 유저의 ID  ex) ID1234
	 	private String uid;
	 	
	 	// 유저의 비밀번호 ex) p@ssw0rd 	
	 	private String upw;
	 	
	 	// 유저의 생일 ex) 000413
	 	private String ubirth;
	 	// 유저 성별 ex) M-남 F-여	 	
	 	private String ugender;
	 	// 유저의 전화번호 ex) (010-****-****)	 	
	 	private String uphone; 	 	
	 	private String uaddr2; // 직접입력(유저가 입력받음)
	 	private String umail; // 항상 [**@*.*] 형태로 입력받음.
	 	
	 	
	 	
//		아래부분들은 유저가 입력하지 않습니다.  
//	    (유저가 입력받지 않음)
	 	private String ustatus;  // 기본값 N, 무시해도 자동으로 N,
 							       // Y-탈퇴 N-정상 B-정지
	 	private String upost; // 카카오맵 API
	 	private String uaddr1; // 카카오맵 API
	 	
	 	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 	private Date uinsertdate; // 가입일
	 	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 	private Date uupdatedate; // 수정일
	 	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 	private Date udeletedate; // 탈퇴일
	 	private String uprofile;
	 	private String usaveprofile;
	 	
	 	public UserVo() {
	 		
	 	}

		public String getUidx() {
			return uidx;
		}

		public void setUidx(String uidx) {
			this.uidx = uidx;
		}

		public String getUname() {
			return uname;
		}

		public void setUname(String uname) {
			this.uname = uname;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}
	
		public String getUpw() {
			return upw;
		}

		public void setUpw(String upw) {
			this.upw = upw;
		}

		public String getUbirth() {
			return ubirth;
		}

		public void setUbirth(String ubirth) {
			this.ubirth = ubirth;
		}

		public String getUgender() {
			return ugender;
		}

		public void setUgender(String ugender) {
			this.ugender = ugender;
		}

		public String getUphone() {
			return uphone;
		}

		public void setUphone(String uphone) {
			this.uphone = uphone;
		}

		public String getUaddr2() {
			return uaddr2;
		}

		public void setUaddr2(String uaddr2) {
			this.uaddr2 = uaddr2;
		}

		public String getUmail() {
			return umail;
		}

		public void setUmail(String umail) {
			this.umail = umail;
		}

		public String getUstatus() {
			return ustatus;
		}

		public void setUstatus(String ustatus) {
			this.ustatus = ustatus;
		}

		public String getUpost() {
			return upost;
		}

		public void setUpost(String upost) {
			this.upost = upost;
		}

		public String getUaddr1() {
			return uaddr1;
		}

		public void setUaddr1(String uaddr1) {
			this.uaddr1 = uaddr1;
		}

		public Date getUinsertdate() {
			return uinsertdate;
		}

		public void setUinsertdate(Date uinsertdate) {
			this.uinsertdate = uinsertdate;
		}

		public Date getUupdatedate() {
			return uupdatedate;
		}

		public void setUupdatedate(Date uupdatedate) {
			this.uupdatedate = uupdatedate;
		}

		public Date getUdeletedate() {
			return udeletedate;
		}

		public void setUdeletedate(Date udeletedate) {
			this.udeletedate = udeletedate;
		}

		public String getUprofile() {
			return uprofile;
		}

		public void setUprofile(String uprofile) {
			this.uprofile = uprofile;
		}

		public String getUsaveprofile() {
			return usaveprofile;
		}

		public void setUsaveprofile(String usaveprofile) {
			this.usaveprofile = usaveprofile;
		}

		@Override
		public String toString() {
			return "UserVo [uidx=" + uidx + ", uname=" + uname + ", uid=" + uid + ", upw=" + upw + ", ubirth=" + ubirth
					+ ", ugender=" + ugender + ", uphone=" + uphone + ", uaddr2=" + uaddr2 + ", umail=" + umail
					+ ", ustatus=" + ustatus + ", upost=" + upost + ", uaddr1=" + uaddr1 + ", uinsertdate="
					+ uinsertdate + ", uupdatedate=" + uupdatedate + ", udeletedate=" + udeletedate + ", uprofile="
					+ uprofile + ", usaveprofile=" + usaveprofile + "]";
		}

		public UserVo(String uidx, String uname, String uid, String upw, String ubirth, String ugender, String uphone,
				String uaddr2, String umail, String ustatus, String upost, String uaddr1, Date uinsertdate,
				Date uupdatedate, Date udeletedate, String uprofile, String usaveprofile) {
			super();
			this.uidx = uidx;
			this.uname = uname;
			this.uid = uid;
			this.upw = upw;
			this.ubirth = ubirth;
			this.ugender = ugender;
			this.uphone = uphone;
			this.uaddr2 = uaddr2;
			this.umail = umail;
			this.ustatus = ustatus;
			this.upost = upost;
			this.uaddr1 = uaddr1;
			this.uinsertdate = uinsertdate;
			this.uupdatedate = uupdatedate;
			this.udeletedate = udeletedate;
			this.uprofile = uprofile;
			this.usaveprofile = usaveprofile;
		}
	 		
	 	
	 	
	 	
}
