package com.expo.dao;

import com.expo.entity.StatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusMasterDao extends JpaRepository<StatusMaster,Integer> {

    StatusMaster findBystatusId(String statusId);

    StatusMaster findByid(int id);

    StatusMaster findByStatus(String status);

}
