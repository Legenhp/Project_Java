package com.expo.dto;

public class ForgotPasswordDto {
	
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "PasswordDto [uname=" + uname + "]";
	}
	

}
