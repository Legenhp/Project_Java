package com.expo.dto;

import lombok.Data;

@Data
public class LoanMasterDto {

    private String createdBy;
    private String updatedBy;
       private String loanType;
       private Long amount;
}
