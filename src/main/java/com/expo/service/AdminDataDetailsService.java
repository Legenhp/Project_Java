package com.expo.service;

import com.expo.dto.*;
import com.expo.entity.RoleData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminDataDetailsService {


    ResponseEntity<RoleCreatedDto> create_role(RoleData roleData);

    ResponseEntity<List<RoleData>> get_all_roles();

    ResponseEntity<RoleCreatedDto> delete_role(RoleCreatedDto role);

    ResponseEntity<RoleCreatedDto> update_role(RoleData role);

    ResponseEntity<RoleAssignByAdminDto> assignRoleByAdmin(RoleAssignByAdminDto roleAssignByAdminDto);


    ResponseEntity<?> add_emp(EmployeeCreatedDto employeeCreatedDto);

    ResponseEntity<?> add_department(DepartmentCreatedDto departmentCreatedDto);

    ResponseEntity<?> update_emp(EmployeeCreatedDto employeeCreatedDto);

    ResponseEntity create_status(StatusMasterDto statusMasterDto);
}

