package com.expo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expo.dto.LoginDto;
import com.expo.dto.NewPassword;
import com.expo.dto.OtpDto;
import com.expo.dto.ForgotPasswordDto;
import com.expo.dto.UserResponse;
import com.expo.dto.UserResponseDto;
import com.expo.dto.UserResponseLoginDto;
import com.expo.entity.User;
import com.expo.service.CustomerDetailsService;

@RestController
@RequestMapping("/user/apiversion")
public class UserController {
@Autowired
CustomerDetailsService userdetail;
	
	@PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> registeruser(@RequestBody User user){
       return userdetail.registeruser(user);		
		
	}
	
	@GetMapping(value="/get_user/{uname}")
	public ResponseEntity<UserResponseDto> get_user(@PathVariable ("uname") String uname){
		return userdetail.get_user(uname);
	}
	
	@PutMapping(value = "/update_user/{uname}/{pass}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDto> update_user(@PathVariable ("uname") String uname,@PathVariable ("pass") String pass){
		
		return userdetail.update_user(uname,pass);
		
	}
	@PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseLoginDto> login(@RequestBody LoginDto login){
		return userdetail.login(login);
		
	}
	@PostMapping(value ="/forgot_pass",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)  
	public ResponseEntity<UserResponse> forgot_pass(@RequestBody ForgotPasswordDto pass){
		return userdetail.forgot_pass(pass);
	}
	@PostMapping(value = "/verify_otp" , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> verify_otp(@RequestBody OtpDto otp ){
		return userdetail.verify_otp(otp);
		
		
	}
	
	@PostMapping(value = "new_password",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> new_Password(@RequestBody NewPassword newPassword){
		
		return userdetail.new_Password(newPassword);
	}
	
	
	
	@DeleteMapping(value = "/delete" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UserResponse> delete(@RequestBody LoginDto delete){
		return userdetail.delete(delete);
		
	}
	
//	ndsfjn
	
}
