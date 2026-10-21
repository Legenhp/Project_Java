package com.expo.controller;

import com.expo.dto.*;
import com.expo.entity.Employee;
import com.expo.entity.RoleData;
import com.expo.entity.StatusMaster;
import com.expo.service.AdminDataDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/apiversion")
public class AdminController {
    @Autowired
    AdminDataDetailsService admindatadetails;

    @PostMapping(value = "/create_role" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleCreatedDto> create_role(@RequestBody RoleData roleData){
        return admindatadetails.create_role(roleData);
    }
    @GetMapping(value = "/get_all_roles" )
    public ResponseEntity<List<RoleData>> get_all_roles(){
        return admindatadetails.get_all_roles();

    }
    @DeleteMapping(value = "/delete_role",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleCreatedDto> delete_role(@RequestBody RoleCreatedDto role ){
        return admindatadetails.delete_role(role);
    }
    @PatchMapping(value = "/update_role",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleCreatedDto> update_role(@RequestBody RoleData role){
        return admindatadetails.update_role(role);
    }
    @PatchMapping(value = "/assign_role_by_admin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleAssignByAdminDto> assignRoleByAdmin(@RequestBody RoleAssignByAdminDto roleAssignByAdminDto){
        return  admindatadetails.assignRoleByAdmin(roleAssignByAdminDto);

    }
@PostMapping(value = "/add_department" ,consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> add_department(@RequestBody DepartmentCreatedDto departmentCreatedDto){
        return  admindatadetails.add_department(departmentCreatedDto);
}


    @PostMapping(value = "/add_emp" ,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> add_emp(@RequestBody EmployeeCreatedDto employeeCreatedDto){
        return admindatadetails.add_emp(employeeCreatedDto);

    };

    @PostMapping(value = "/update_emp",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update_emp(@RequestBody EmployeeCreatedDto employeeCreatedDto)
    {
        return admindatadetails.update_emp(employeeCreatedDto);
    }

    @PostMapping(value = "/create_status" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create_status(@Valid @RequestBody StatusMasterDto statusMasterDto){
        return admindatadetails.create_status(statusMasterDto);

    }

}
