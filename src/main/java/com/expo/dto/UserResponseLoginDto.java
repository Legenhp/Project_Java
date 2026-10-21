package com.expo.dto;

public class UserResponseLoginDto {

	private String email;
	private String status;
	private String regid;
	private String msg;
	private String url;
	@Override
	public String toString() {
		return "UserResponseLoginDto [email=" + email + ", status=" + status + ", regid=" + regid + ", msg=" + msg
				+ ", url=" + url + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
