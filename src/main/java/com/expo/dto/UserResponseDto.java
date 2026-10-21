package com.expo.dto;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserResponseDto {

	private String uname;
	private String lname;
	private long    contactno;
	private String email;
	private String msg;

	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getContactno() {
		return contactno;
	}
	public void setContactno(long contactno) {
		this.contactno = contactno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "UserResponseDto [uname=" + uname + ", lname=" + lname + ", contactno=" + contactno + ", email=" + email
				+ ", msg=" + msg + "]";
	}
}
