 package com.kmove.app.user.vo;

public class LoginVo {
	
	
	private String uid;
	private String upw;
	private String UIDX;
	
	public LoginVo() {
		
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

	public String getUIDX() {
		return UIDX;
	}

	public void setUIDX(String uIDX) {
		UIDX = uIDX;
	}

	public LoginVo(String uid, String upw, String uIDX) {
		super();
		this.uid = uid;
		this.upw = upw;
		UIDX = uIDX;
	}

	@Override
	public String toString() {
		return "LoginVo [uid=" + uid + ", upw=" + upw + ", UIDX=" + UIDX + "]";
	}

	
	
}
