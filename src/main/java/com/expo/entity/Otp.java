package com.expo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="MS_client_crud_otp")
@Data
public class Otp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;

	private String otp;
	@CreationTimestamp
	private LocalDateTime createdAt;

	private LocalDateTime expireAt;
	private String createdBy;
	private String updatedBy;


}
