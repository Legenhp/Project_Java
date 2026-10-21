package com.expo.service;


import com.expo.dto.LoanMasterDto;
import org.springframework.http.ResponseEntity;

public interface LoanMasterService {

    ResponseEntity<?> addLoanMasterInfo(LoanMasterDto loanMasterDto);

    ResponseEntity<?> getAllTypesOfLoan();
}
