package com.expo.dao;

import com.expo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

    Employee findByEmployeeCodeOrEmployeeId(String employeecode,int id);


    Employee findByEmployeeCode(String employeeCode);
}
