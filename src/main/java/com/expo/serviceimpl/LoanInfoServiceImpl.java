package com.expo.serviceimpl;

import com.expo.dao.EmployeeDao;
import com.expo.dao.LoanInfoDao;
import com.expo.dao.LoanMasterDao;
import com.expo.dao.StatusMasterDao;
import com.expo.dto.EmployeeCreatedDto;
import com.expo.dto.LoanInfoDto;
import com.expo.dto.StatusUpdateDto;
import com.expo.entity.Employee;
import com.expo.entity.LoanInfo;
import com.expo.entity.LoanMaster;
import com.expo.entity.StatusMaster;
import com.expo.service.LoanInfoService;
import com.expo.util.UserRegIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanInfoServiceImpl implements LoanInfoService {
    @Autowired
    LoanInfoDao loanInfoDao;
    @Autowired
    StatusMasterDao statusMasterDao;

    @Autowired
    LoanMasterDao loanMasterDao;
    @Autowired
    EmployeeDao employeeDao;
    Logger LOGGER = LogManager.getLogger(LoanInfoServiceImpl.class);




    @Override
    public ResponseEntity<?> new_Loan_Info(LoanInfoDto loanInfoDto) {

        LoanInfoDto loanInfoDto1 = new LoanInfoDto();
        if (!ObjectUtils.isEmpty(loanInfoDto)) {
            int id = UserRegIdGenerator.UserIdregGenerator();
            LoanInfo loanInfo = loanInfoDao.findByloanId(id+"LMS");

            if (loanInfo == null) {
                loanInfo = new LoanInfo();
                loanInfo.setLoanId(id+"LMS");
                loanInfo.setLoanApplierName(loanInfoDto.getLoanApplierName());
                loanInfo.setDateOfBirth(loanInfoDto.getDateOfBirth());
                loanInfo.setEmail(loanInfoDto.getEmail());
                loanInfo.setCompanyEmail(loanInfoDto.getCompanyEmail());
                loanInfo.setPassword(loanInfoDto.getPassword());
                loanInfo.setContactNumber(loanInfoDto.getContactNumber());
                loanInfo.setAdharNumber(loanInfoDto.getAdharNumber());
                loanInfo.setPanNumber(loanInfoDto.getPanNumber());
                loanInfo.setGender(loanInfoDto.getGender());
                loanInfo.setPermantAddress(loanInfoDto.getPermantAddress());
                loanInfo.setCurrentAddress(loanInfoDto.getCurrentAddress());
                loanInfo.setCompanyAddress(loanInfoDto.getCompanyAddress());
                loanInfo.setEmployeeNumber(loanInfoDto.getEmployeeNumber());
                loanInfo.setEmpType(loanInfoDto.getEmpType());
                loanInfo.setDesignation(loanInfoDto.getDesignation());
                loanInfo.setOccupation(loanInfoDto.getOccupation());
                loanInfo.setCompanyName(loanInfoDto.getCompanyName());
                loanInfo.setMonthlySalary(loanInfoDto.getMonthlySalary());
                loanInfo.setExistingemi(loanInfoDto.getExistingemi());
                loanInfo.setReqLoanAmount(loanInfoDto.getReqLoanAmount());
                loanInfo.setGuranterOne(loanInfoDto.getGuranterOne());
                loanInfo.setGuranterTwo(loanInfoDto.getGuranterTwo());
                loanInfo.setGuranterOneContact(loanInfoDto.getGuranterOneContact());
                loanInfo.setGuranterTwoContact(loanInfoDto.getGuranterTwoContact());
                loanInfo.setCreatedDate(LocalDateTime.now());
                loanInfo.setUpdatedDate(LocalDateTime.now());
                loanInfo.setCreatedBy(loanInfoDto.getCreatedBy());
                loanInfo.setUpdatedBy(loanInfoDto.getUpdatedBy());
                LoanMaster loanMaster=loanMasterDao.findByloanMasterId(loanInfoDto.getLoanMasterId());
                loanInfo.setLoanType(loanMaster);
                StatusMaster statusMaster=statusMasterDao.findByid(1);
                loanInfo.setStatus(statusMaster.getStatus());
                System.out.println("before save");
                loanInfoDao.save(loanInfo);
                LOGGER.info("LOan saved successfully");
                loanInfoDto1.setLoanApplierName(loanInfoDto.getLoanApplierName());
                loanInfoDto1.setReqLoanAmount(loanInfoDto.getReqLoanAmount());
                return ResponseEntity.status(HttpStatus.CREATED).body(loanInfoDto1);


            } else {
                new_Loan_Info(loanInfoDto);
            }


        }
        LOGGER.info("wrong req");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong req");



    }


    @Override
    public ResponseEntity<?> update_loan(LoanInfoDto loanInfoDto) {
        if (loanInfoDto == null || !StringUtils.hasText(loanInfoDto.getLoanId())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Loan ID is required or Fields are empty");
        }

        // Find existing loan
        LoanInfo loanInfo =
                loanInfoDao.findByloanId(loanInfoDto.getLoanId().trim());

        if (loanInfo == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Loan not found: " + loanInfoDto.getLoanId());
        }

        // Applicant details
        loanInfo.setLoanApplierName(loanInfoDto.getLoanApplierName());
        loanInfo.setDateOfBirth(loanInfoDto.getDateOfBirth());
        loanInfo.setEmail(loanInfoDto.getEmail());
        loanInfo.setCompanyEmail(loanInfoDto.getCompanyEmail());
        loanInfo.setContactNumber(loanInfoDto.getContactNumber());
        loanInfo.setAdharNumber(loanInfoDto.getAdharNumber());
        loanInfo.setPanNumber(loanInfoDto.getPanNumber());
        loanInfo.setGender(loanInfoDto.getGender());

        // Address
        loanInfo.setPermantAddress(loanInfoDto.getPermantAddress());
        loanInfo.setCurrentAddress(loanInfoDto.getCurrentAddress());
        loanInfo.setCompanyAddress(loanInfoDto.getCompanyAddress());

        // Employment
        loanInfo.setEmployeeNumber(loanInfoDto.getEmployeeNumber());
        loanInfo.setEmpType(loanInfoDto.getEmpType());
        loanInfo.setDesignation(loanInfoDto.getDesignation());
        loanInfo.setOccupation(loanInfoDto.getOccupation());
        loanInfo.setCompanyName(loanInfoDto.getCompanyName());

        // Financial details
        loanInfo.setMonthlySalary(loanInfoDto.getMonthlySalary());
        loanInfo.setExistingemi(loanInfoDto.getExistingemi());
        loanInfo.setReqLoanAmount(loanInfoDto.getReqLoanAmount());

        // Guarantor details
        loanInfo.setGuranterOne(loanInfoDto.getGuranterOne());
        loanInfo.setGuranterTwo(loanInfoDto.getGuranterTwo());
        loanInfo.setGuranterOneContact(loanInfoDto.getGuranterOneContact());
        loanInfo.setGuranterTwoContact(loanInfoDto.getGuranterTwoContact());

        // Status
        loanInfo.setStatus("OM_PENDING");

        // Loan Master
        if (StringUtils.hasText(loanInfoDto.getLoanMasterId())) {

            LoanMaster loanMaster =
                    loanMasterDao.findByloanMasterId(
                            loanInfoDto.getLoanMasterId().trim());

            if (loanMaster == null) {

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Loan Master not found: "
                                + loanInfoDto.getLoanMasterId());
            }

            loanInfo.setLoanType(loanMaster);
        }

        // Audit
        loanInfo.setUpdatedBy(loanInfoDto.getUpdatedBy());

        loanInfoDao.save(loanInfo);

        LOGGER.info("Loan updated successfully: {}",
                loanInfoDto.getLoanId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Loan updated successfully");
    }



    @Override
    public ResponseEntity<List<LoanInfo>> get_all_loans() {

        return  ResponseEntity.status(HttpStatus.OK).body(loanInfoDao.findAll());
    }
    @Override
    public ResponseEntity<?> get_loans() {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Employee employee=employeeDao.findByEmployeeCode(username);
        if (employee.getDepartment().getDepartmentId()==3 ){

            String omPending = "OM_PENDING";
            List<LoanInfo> loanInfos= loanInfoDao.findByStatusOrStatus(omPending,null);
            return ResponseEntity.status(HttpStatus.FOUND).body(loanInfos);


        }
        if (employee.getDepartment().getDepartmentId()==1){

            String amPending = "AM_PENDING";
            List<LoanInfo> loanInfos= loanInfoDao.findByStatusOrStatus(amPending,null);
            return ResponseEntity.status(HttpStatus.FOUND).body(loanInfos);
        }

        if (employee.getDepartment().getDepartmentId()==2){


            String rmPending1="REJECTED";
            List<LoanInfo> loanInfos= loanInfoDao.findByStatusOrStatus(null,rmPending1);
            return ResponseEntity.status(HttpStatus.FOUND).body(loanInfos);
        }
        if (employee.getDepartment().getDepartmentId()==4){

            String bmPending = "BM_PENDING";
            List<LoanInfo> loanInfos= loanInfoDao.findByStatusOrStatus(bmPending,null);
            return ResponseEntity.status(HttpStatus.FOUND).body(loanInfos);
        }
        if (employee.getDepartment().getDepartmentId()==5){

            String cmPending = "CM_PENDING";
            List<LoanInfo> loanInfos= loanInfoDao.findByStatusOrStatus(cmPending,null);
            return ResponseEntity.status(HttpStatus.FOUND).body(loanInfos);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("YOU Dont have a department !");
    }

    @Override
    public ResponseEntity<?> update_status(StatusUpdateDto statusUpdateDto) {
        if (statusUpdateDto==null || !StringUtils.hasText( statusUpdateDto.getLoanId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO content ");

        }
        LoanInfo loanInfo=loanInfoDao.findByloanId(statusUpdateDto.getLoanId());
        if (loanInfo==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong loan ID");
        }

        if (statusUpdateDto.getAcceptReject()==1){

            String status =loanInfo.getStatus();
            StatusMaster statusMaster=statusMasterDao.findByStatus(status.trim());
            int q=statusMaster.getId()+1;
            StatusMaster  statusMaster1=statusMasterDao.findByid(q);
            loanInfo.setStatus(statusMaster1.getStatus().trim());
            loanInfoDao.save(loanInfo);
            return ResponseEntity.status(HttpStatus.OK).body("Status Updated");
        }
        loanInfo.setStatus("REJECTED");
        loanInfoDao.save(loanInfo);
        return ResponseEntity.status(HttpStatus.OK).body("Status updated");
    }

}
