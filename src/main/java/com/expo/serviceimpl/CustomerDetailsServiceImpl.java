package com.expo.serviceimpl;
import com.expo.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.expo.dao.OtpDao;
import com.expo.dao.UserDao;
import com.expo.dto.ForgotPasswordDto;
import com.expo.dto.LoginDto;
import com.expo.dto.NewPassword;
import com.expo.dto.OtpDto;
import com.expo.dto.UserResponse;
import com.expo.dto.UserResponseDto;
import com.expo.entity.Otp;
import com.expo.entity.User;
import com.expo.util.UserRegIdGenerator;
import com.expo.dto.UserResponseLoginDto;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	@Autowired
	UserDao userdao;
	//@Autowired
//	private JavaMailSender mail;
	@Autowired
	OtpDao otpDao;
	
	private static final Logger LOGGER=LogManager.getLogger(CustomerDetailsServiceImpl.class);
	@Override
	public ResponseEntity<UserResponse> registeruser(User user) {
		LOGGER.info("User Data insertion start");
		UserResponse userdto=new UserResponse();
		
        if (user!=null) {
    		
        	user.setRegid(UserRegIdGenerator.UserIdregGenerator()+"LMS");
			user.setStatus("ACTIVE");
			User user1=userdao.save(user);
        	userdto.setMsg("User "+user1.getLname()+" is created!");
			return new ResponseEntity<UserResponse>(userdto,HttpStatus.OK);
		}
		
		LOGGER.info("User Data insertion End");
		// TODO Auto-generated method stub
		userdto.setMsg("User not created!");	
		return new ResponseEntity<UserResponse>(userdto,HttpStatus.NO_CONTENT);
		
	}
	@Override
	public ResponseEntity<UserResponseDto> get_user(String uname) {
		// TODO Auto-generated method stub
		LOGGER.info("Start of fetch method");
		User user1=userdao.findByUsername(uname);
		UserResponseDto userdto=new UserResponseDto();//=null check
		if(!ObjectUtils.isEmpty(user1)) {
			userdto.setUname(user1.getUsername());
			userdto.setEmail(user1.getEmail());
			userdto.setContactno(user1.getContactno());
			userdto.setLname(user1.getLname());
			userdto.setMsg("User is found successfully");
			return new ResponseEntity<UserResponseDto>(userdto,HttpStatus.FOUND);
		}
		
        LOGGER.info("End of fetch");
        userdto.setMsg("NOT Found!!");
		return new ResponseEntity<UserResponseDto>(userdto,HttpStatus.NOT_FOUND);
	}
	@Override
	public ResponseEntity<UserResponseDto> update_user(String uname, String pass) {
		// TODO Auto-generated method stub
		LOGGER.info("Update op start");
		User user=userdao.findByUsernameAndPassword(uname, pass);
		if(!ObjectUtils.isEmpty(user)) {
			
			
			
		}
		
		return null;
	}
	@Override
	public ResponseEntity<UserResponseLoginDto> login(LoginDto login) {
		// TODO Auto-generated method stub
		LOGGER.info("Start of login op");
		
		UserResponseLoginDto userdto=new UserResponseLoginDto();
		if(!ObjectUtils.isEmpty(login)) {
			if((login.getUname()!=null && login.getPass()!=null)&&(login.getUname()!="" && login.getPass()!="")) {
			
			User user = userdao.findByUsernameOrEmailAndPasswordAndStatus(login.getUname(), login.getUname(), login.getPass(),"ACTIVE");
			if(!ObjectUtils.isEmpty(user)) {
			userdto.setEmail(user.getEmail());
			userdto.setRegid(user.getRegid());
			userdto.setMsg("User is found ");
			return new ResponseEntity<UserResponseLoginDto>(userdto,HttpStatus.FOUND);
			}
		}}
		
		userdto.setMsg("User NOT Found!");
		LOGGER.info("END of login op");
		return new ResponseEntity<UserResponseLoginDto>(userdto,HttpStatus.NOT_FOUND);
	}
	@Override
	public ResponseEntity<UserResponse> forgot_pass(ForgotPasswordDto pass) {
		// TODO Auto-generated method stub
		User user =userdao.findByUsernameOrEmail(pass.getUname(),pass.getUname());
		UserResponse userdto=new UserResponse();
		Otp otp4=otpDao.findByCreatedBy(pass.getUname());
//		if(!ObjectUtils.isEmpty(user) && ("active".equals(user.getStatus().trim().toLowerCase(Locale.ROOT)))) {
//			if(!ObjectUtils.isEmpty(otp4)) {
//                         otpDao.delete(otp4);
//			}
//
//			String otp1=OtpGenerator.otpgenerator();
//			SimpleMailMessage mess = new SimpleMailMessage();
//			mess.setTo("zayeedinamdar@gmail.com");
//			mess.setSubject("OTP");
//			mess.setText(otp1+ " ");
//			mail.send(mess);
//			userdto.setMsg("OTP Send");
//			Otp otp=new Otp();
//
//			otp.setOtp(otp1);
//			otp.setCreatedAt(LocalDateTime.now());
//			otp.setExpireAt(LocalDateTime.now().plusMinutes(5));
//			otp.setCreatedAt(LocalDateTime.now());
//			if(user.getEmail()==null) {
//
//			otp.setCreatedBy(user.getUsername());}
//			else {
//				otp.setCreatedBy(user.getEmail());
//			}
//		    otpDao.save(otp);
//			return new ResponseEntity<UserResponse>(userdto,HttpStatus.OK);
//		}
		
		userdto.setMsg("Username or email is wrong");
		LOGGER.info("END op of forgot pass");
		return new ResponseEntity<UserResponse>(userdto,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@Override
	public ResponseEntity<UserResponse> verify_otp(OtpDto otp) {
		// TODO Auto-generated method stub
		
	LOGGER.info("Start op of verify msg");
		UserResponse user=new UserResponse();
		if((!ObjectUtils.isEmpty(otp) )&&(!otp.getOtp().isEmpty())) {
		   Otp otp1=otpDao.findByCreatedBy(otp.getCreatedBy());
		
				if(otp.getOtp().equals(otp1.getOtp())&&(LocalDateTime.now().isBefore(otp1.getExpireAt())) ) {
					
					user.setMsg("OTP is verified!");
					user.setMsg("You can change your password");
					return new ResponseEntity<UserResponse>(user,HttpStatus.ACCEPTED);
					
			}
				else {
					user.setMsg("OTP expired!");
					return new ResponseEntity<UserResponse>(user,HttpStatus.BAD_REQUEST);
					
				}
			
		}
		else {
			user.setMsg("Please correct OTP!");
			return new ResponseEntity<UserResponse>(user,HttpStatus.BAD_REQUEST);
		}
		

	}
	
	@Override
	public ResponseEntity<UserResponse> new_Password(NewPassword newPassword) {
		// TODO Auto-generated method stub
		LOGGER.info("Start of new_passwrod");
		
		UserResponse userdto=new UserResponse();
		if(!ObjectUtils.isEmpty(newPassword) && (!newPassword.getNewPass().isEmpty()) && (!newPassword.getReTypePass().isEmpty() )&&(newPassword.equals(newPassword.getReTypePass()))) {
			userdto.setMsg("New pass created!");
			User user=new User();
			user.setPassword(newPassword.getReTypePass());
			return new ResponseEntity<UserResponse>(userdto,HttpStatus.OK);
			
		}
		userdto.setMsg("Password do not Match or empty field sent!");
		 return new ResponseEntity<UserResponse>(userdto,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<UserResponse> delete(LoginDto delete) {
		// TODO Auto-generated method stub
		User user = userdao.findByUsernameOrEmailAndPasswordAndStatus(delete.getUname(), delete.getUname(), delete.getPass(), "ACTIVE");
		UserResponse userdto=new UserResponse();
		if(!ObjectUtils.isEmpty(user)) {
			user.setStatus("INACTIVE");
			userdao.save(user);
			userdto.setMsg("User delete");
			return new ResponseEntity<UserResponse>(userdto,HttpStatus.OK);
			
		}
		userdto.setMsg("USER NOT FOUND!");
		return new ResponseEntity<UserResponse>(userdto,HttpStatus.NOT_FOUND);
	}

	

}


//{
//	  
//	  "fname": "Ruru",
//	  "lname": "Thakur",
//	  "email": "ruru@gmail.com",
//	  "username": "ruru123",
//	  "password": "ruruthakur123",
//	  "contactno": 111111111111
