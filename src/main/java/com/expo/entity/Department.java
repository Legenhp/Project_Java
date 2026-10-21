package com.expo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MS_CLient_Crud_Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    private String departmentCode;


    @NotBlank
    private String departmentName;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "Department_Head_Id")
    private Employee departmentHead;


    private String status;

    @NotBlank
    private String createdBy;

    private String updatedBy;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}