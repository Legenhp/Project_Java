package com.expo.controller;

import com.expo.dto.LoanMasterDto;
import com.expo.entity.RoleData;
import com.expo.service.LoanMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.awt.*;

@RestController
@RequestMapping("/admin/apiversion")
public class LoanMasterController {
      @Autowired
      LoanMasterService loanmasterservice;

    @PostMapping(value = "/new_loan_type",produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLoanMaster(@RequestBody LoanMasterDto loanmasterDto) {
      return loanmasterservice.addLoanMasterInfo(loanmasterDto);
    };

    @GetMapping(value = "/get_all_types_of_loan",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTypesOfLoan(){
        return loanmasterservice.getAllTypesOfLoan();
    };



}
