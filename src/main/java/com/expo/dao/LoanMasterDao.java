package com.expo.dao;

import com.expo.entity.LoanMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanMasterDao extends CrudRepository<LoanMaster, Integer> {


    LoanMaster findByloanMasterId(String loanMasterId);

}
