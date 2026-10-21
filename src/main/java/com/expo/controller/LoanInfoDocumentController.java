package com.expo.controller;


import com.expo.dto.UserResponseLoginDto;
import com.expo.service.LoanInfoDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/loan_doc/apiversion")
public class LoanInfoDocumentController {

    @Autowired
    LoanInfoDocumentService loanInfoDocumentService;
    @PostMapping(value = "/loan_document_create/{email}/{loanId}",consumes = MediaType.ALL_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loan_document_create(@PathVariable ("email") String email ,@PathVariable ("loanId") String loanId,@RequestParam ("files") List<MultipartFile> files) {
        return loanInfoDocumentService.loan_document_create(email,loanId,files);

    };
}
