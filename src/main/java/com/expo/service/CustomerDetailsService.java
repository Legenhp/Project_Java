package com.expo.service;

import org.springframework.http.ResponseEntity;

import com.expo.dto.LoginDto;
import com.expo.dto.NewPassword;
import com.expo.dto.OtpDto;
import com.expo.dto.ForgotPasswordDto;
import com.expo.dto.UserResponse;
import com.expo.dto.UserResponseDto;
import com.expo.entity.User;

public interface CustomerDetailsService {
	
	ResponseEntity<UserResponse>  registeruser(User user);

	ResponseEntity<UserResponseDto> get_user(String uname);

	ResponseEntity<UserResponseDto> update_user(String uname, String pass);

	ResponseEntity<com.expo.dto.UserResponseLoginDto> login(LoginDto login);

	ResponseEntity<UserResponse> forgot_pass(ForgotPasswordDto pass);

	ResponseEntity<UserResponse> delete(LoginDto delete);

	ResponseEntity<UserResponse> verify_otp(OtpDto otp);

	ResponseEntity<UserResponse> new_Password(NewPassword newPassword);

}
