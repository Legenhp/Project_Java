package com.expo.serviceimpl;

import com.expo.dao.LoanInfoDao;
import com.expo.dao.LoanInfoDocumentDao;
import com.expo.dto.UserResponseDto;
import com.expo.dto.UserResponseLoginDto;
import com.expo.entity.LoanInfo;
import com.expo.entity.LoanInfoDocuments;
import com.expo.service.LoanInfoDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class LoanInfoDocumentServiceImpl implements LoanInfoDocumentService {

    @Autowired
    LoanInfoDao loanInfoDao;
    @Autowired
    LoanInfoDocumentDao loanInfoDocumentDao;

    @Override
    public ResponseEntity<?> loan_document_create(String email,String loanId, List<MultipartFile> files) {
            UserResponseLoginDto userResponseLoginDto = new UserResponseLoginDto();
        if (!email.isEmpty() && !loanId.isEmpty() && !ObjectUtils.isEmpty(files)) {

            LoanInfo loanInfo = loanInfoDao.findLoanInfoByloanIdAndEmail(loanId, email);
            for (MultipartFile file : files) {

                //   LoanInfoDocuments loanInfoDocuments = new LoanInfoDocuments();
                try {
                    LoanInfoDocuments loanInfoDocuments = new LoanInfoDocuments();
                    loanInfoDocuments.setLoanInfodocument(file.getBytes());
                    loanInfoDocuments.setDoctype(file.getContentType());
                    loanInfoDocuments.setDocname(file.getOriginalFilename());
                    loanInfoDocumentDao.save(loanInfoDocuments);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            userResponseLoginDto.setMsg("SAVED");
            return ResponseEntity.status(HttpStatus.OK).body(userResponseLoginDto);
        }
           userResponseLoginDto.setMsg("email or loanId or file empty");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseLoginDto);
    }
}