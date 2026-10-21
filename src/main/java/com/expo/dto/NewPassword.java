package com.expo.dto;

public class NewPassword {

	private String uname;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getReTypePass() {
		return reTypePass;
	}
	public void setReTypePass(String reTypePass) {
		this.reTypePass = reTypePass;
	}
	private String newPass;
	private String reTypePass;

	@Override
	public String toString() {
		return "NewPassword [uname=" + uname + ", newPass=" + newPass + ", reTypePass=" + reTypePass + "]";
	}
	
}
