package com.expo.serviceimpl;

import com.expo.dao.LoanMasterDao;
import com.expo.dto.LoanMasterDto;
import com.expo.dto.UserResponseDto;
import com.expo.entity.LoanMaster;
import com.expo.service.LoanMasterService;
import com.expo.util.UserRegIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanMasterServiceImpl implements LoanMasterService {

    @Autowired
    LoanMasterDao  loanMasterDao;
Logger LOGGER = LogManager.getLogger(LoanMasterServiceImpl.class);

    @Override
    public ResponseEntity<?> addLoanMasterInfo(LoanMasterDto loanMasterDto) {
        LOGGER.info("addLoanMasterInfo method started");
        UserResponseDto userResponseDto = new UserResponseDto();
        if(!ObjectUtils.isEmpty(loanMasterDto)){
            int loanId=UserRegIdGenerator.UserIdregGenerator();
          LoanMaster loanMaster = loanMasterDao.findByloanMasterId(loanId+"");
          if(ObjectUtils.isEmpty(loanMaster)){
                LoanMaster loanMaster1 = new LoanMaster();
                loanMaster1.setLoanMasterId("LMS"+loanId);
                loanMaster1.setLoanType(loanMasterDto.getLoanType());
                loanMaster1.setAmount(loanMasterDto.getAmount());
                loanMaster1.setCreatedBy(loanMasterDto.getCreatedBy());
                loanMasterDao.save(loanMaster1);
                LOGGER.info("addLoanMasterInfo method ENDED");
                userResponseDto.setLname(loanMasterDto.getLoanType());
                userResponseDto.setContactno(loanMasterDto.getAmount());
                userResponseDto.setMsg("SAved Successfully");
                return  ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
          }
          else{
              addLoanMasterInfo(loanMasterDto);
          }

        }
        userResponseDto.setMsg("Invalid data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
    }

    @Override
    public ResponseEntity<?> getAllTypesOfLoan() {
        List<LoanMaster> listOfAllLoans=new ArrayList<LoanMaster>();
        listOfAllLoans= (List<LoanMaster>) loanMasterDao.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(listOfAllLoans);
    }


}
