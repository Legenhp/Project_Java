package com.expo.dao;

import com.expo.entity.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface   RoleDataDao extends JpaRepository<RoleData, Integer> {

    RoleData findByRoleName(String roleName);

}
