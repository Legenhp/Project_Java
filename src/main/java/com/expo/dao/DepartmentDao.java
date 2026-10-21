package com.expo.dao;

import com.expo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department,Integer> {
       Department findByDepartmentId(int departmentId);
}
