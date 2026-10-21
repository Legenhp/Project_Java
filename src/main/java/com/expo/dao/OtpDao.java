package com.expo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expo.entity.Otp;

@Repository
public interface OtpDao extends JpaRepository<Otp, Integer> {

	Otp findByCreatedBy(String createdBy );
	
	
}
