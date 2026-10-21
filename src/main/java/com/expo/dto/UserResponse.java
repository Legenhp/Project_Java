package com.expo.dto;

public class UserResponse {
	
	private String msg;
	private String url;
	public String getMsg() {
		return msg;
	}
	@Override
	public String toString() {
		return "UserResponse [msg=" + msg + ", url=" + url + "]";
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
