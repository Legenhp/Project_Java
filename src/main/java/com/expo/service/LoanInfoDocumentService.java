package com.expo.service;


import com.expo.dto.UserResponseDto;
import com.expo.dto.UserResponseLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoanInfoDocumentService {

    ResponseEntity<?> loan_document_create(String email,String loanId, List<MultipartFile> files);
}
