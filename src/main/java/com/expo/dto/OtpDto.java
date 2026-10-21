package com.expo.dto;

public class OtpDto {
	
	private String otp;
	private String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OtpDto [otp=" + otp + ", createdBy=" + createdBy + "]";
	}

}
