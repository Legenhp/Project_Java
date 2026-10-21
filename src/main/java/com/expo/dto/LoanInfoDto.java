package com.expo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanInfoDto {

private String loanId;
    private String loanApplierName;

    private String dateOfBirth;

    private String email;

    private String companyEmail;

    private String contactNumber;

    private String AdharNumber;

    private String panNumber;

    private String password;
    private String loanMasterId;
    private String gender;

    private String permantAddress;

    private String currentAddress;

    private String companyAddress;

    private String employeeNumber;

    private String empType;

    private String designation;

    private String occupation;

    private String companyName;

    private String monthlySalary;

    private String existingemi;

    private String reqLoanAmount;

    private String guranterOne;

    private String guranterTwo;

    private String guranterOneContact;

    private String guranterTwoContact;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String createdBy;

    private String updatedBy;
}
