package com.expo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import sun.security.util.Password;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "MS_client_crud_LoanInfo")
public class LoanInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String loanId;

    private String loanApplierName;

    private String dateOfBirth;

    private String email;

    private String companyEmail;

    private String contactNumber;

    private String AdharNumber;

    private String panNumber;

    private String gender;

    private String permantAddress;

    private String currentAddress;

    private String companyAddress;

    private String employeeNumber;

    @ManyToOne
    private LoanMaster loanType;

    private String empType;

    private String designation;

    private String occupation;

    private String password;

    private String companyName;

    private String monthlySalary;

    private String existingemi;

    private String reqLoanAmount;

    private String guranterOne;

    private String guranterTwo;

    private String guranterOneContact;

    private String guranterTwoContact;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    @OneToMany
    private List<LoanInfoDocuments> loanInfoDocuments;

    @ManyToOne
    @JoinColumn(name = "RM_id")
    private Employee employee;

    private String status;

}
