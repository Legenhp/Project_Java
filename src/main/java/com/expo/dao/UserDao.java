package com.expo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expo.entity.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String uname);
	
	User findByUsernameAndPassword(String uname,String pass);
	
	User findByUsernameOrEmailAndPasswordAndStatus(String uname,String email,String pass,String status);
	
	User findByUsernameOrEmail(String uname,String email);
	
	
	
}
