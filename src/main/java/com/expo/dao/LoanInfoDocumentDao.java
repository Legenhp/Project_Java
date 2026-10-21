package com.expo.dao;

import com.expo.entity.LoanInfoDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanInfoDocumentDao extends JpaRepository<LoanInfoDocuments,Integer> {
}
