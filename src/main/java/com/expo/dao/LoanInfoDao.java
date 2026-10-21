package com.expo.dao;

import com.expo.entity.LoanInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanInfoDao extends JpaRepository<LoanInfo, Integer> {

LoanInfo findByloanId(String loan_id);
LoanInfo findLoanInfoByloanIdAndEmail(String loan_id,String email);

    List<LoanInfo> findByStatusOrStatus(String status,String status1);
}
