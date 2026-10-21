package com.expo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MS_client_crud_role")
@Data
public class RoleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int roleId;
    private String roleName;
    private String description;
    private String createdBy;
    @CreationTimestamp
    private LocalDateTime createdDate;

    private String updatedBy;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private String status;


}
