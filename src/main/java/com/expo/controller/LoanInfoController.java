package com.expo.controller;

import com.expo.dto.LoanInfoDto;
import com.expo.dto.StatusUpdateDto;
import com.expo.entity.LoanInfo;
import com.expo.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loan/apiversion")
public class LoanInfoController {
@Autowired
LoanInfoService loanInfoService;

    @PostMapping(value = "/new_loan_info" ,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> new_Loan_Info(@RequestBody LoanInfoDto loanInfoDto){
        return loanInfoService.new_Loan_Info(loanInfoDto);
    }
    @GetMapping(value = "/get_all_loans" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoanInfo>> get_all_loans(){
        return loanInfoService.get_all_loans();
    }

    @PutMapping(value =  "update_loan" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update_loan(@RequestBody LoanInfoDto loanInfoDto){
    return loanInfoService.update_loan(loanInfoDto);}

        @GetMapping(value = "/get_loans")
        ResponseEntity<?> get_loans(){
                       return loanInfoService.get_loans();

        };

    @PatchMapping(value = "update_status",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update_status(@Valid @RequestBody StatusUpdateDto statusUpdateDto){
        return loanInfoService.update_status(statusUpdateDto);
    }

    }





