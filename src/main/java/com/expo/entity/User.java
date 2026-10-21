package com.expo.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MS_client_crud")
@Data
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String  username;
	private String  password;
	private long    contactno;
	private String regid;
	private String status;
	@ManyToOne(cascade = CascadeType.ALL)
	private RoleData roleData;
	@CreationTimestamp
    private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	private String createdBy;
	private String updatedBy;


}
