package com.expo.service;

import com.expo.dto.EmployeeCreatedDto;
import com.expo.dto.LoanInfoDto;
import com.expo.dto.StatusUpdateDto;
import com.expo.entity.LoanInfo;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface LoanInfoService {


    ResponseEntity<?> new_Loan_Info(LoanInfoDto loanInfoDto);

    ResponseEntity<List<LoanInfo>> get_all_loans();

    ResponseEntity<?> update_loan(LoanInfoDto loanInfoDto);

    ResponseEntity<?> get_loans();

    ResponseEntity<?> update_status(@Valid StatusUpdateDto statusUpdateDto);
}
