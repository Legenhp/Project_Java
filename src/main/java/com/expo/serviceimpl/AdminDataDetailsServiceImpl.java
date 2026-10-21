package com.expo.serviceimpl;

import com.expo.dao.*;
import com.expo.dto.*;
import com.expo.entity.*;
import com.expo.service.AdminDataDetailsService;
import com.expo.util.UserRegIdGenerator;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@Service
public class AdminDataDetailsServiceImpl implements AdminDataDetailsService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    RoleDataDao roleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    StatusMasterDao statusMasterDao;
    private static final Logger LOGGER = LogManager.getLogger(AdminDataDetailsServiceImpl.class);


    @Override
    public ResponseEntity<RoleCreatedDto> create_role(RoleData roleData) {
        LOGGER.info("create_role method started");
        RoleCreatedDto roleCreatedDto = new RoleCreatedDto();
        RoleData role = roleDao.findByRoleName(roleData.getRoleName());
        if (ObjectUtils.isEmpty(role) && (!ObjectUtils.isEmpty(roleData))) {
            RoleData role1 = new RoleData();
            role1.setRoleName(roleData.getRoleName());//null pointer due to To lower case check it ->.toLowerCase(Locale.ROOT)
            role1.setCreatedBy(roleData.getCreatedBy());
            role1.setRoleId(roleData.getRoleId());
            role1.setStatus("ACTIVE");
            role1.setDescription(roleData.getDescription());
            role1.setUpdatedBy(roleData.getUpdatedBy());
            role1.setCreatedDate(LocalDateTime.now());
            roleDao.save(role1);

            LOGGER.info("create_role Saved Successfully");
            roleCreatedDto.setRoleName(role1.getRoleName());
            roleCreatedDto.setRoleDescription(roleData.getDescription());
            roleCreatedDto.setMsg("Role created successfully");
            return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.CREATED);

        }
        roleCreatedDto.setRoleName(roleData.getRoleName());
        roleCreatedDto.setRoleDescription(roleData.getDescription());
        roleCreatedDto.setMsg("Role Already Exists");


        return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<List<RoleData>> get_all_roles() {
        return new ResponseEntity<>(roleDao.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleCreatedDto> delete_role(RoleCreatedDto role) {
        LOGGER.info("Delete_role method started");
        RoleCreatedDto roleCreatedDto = new RoleCreatedDto();
        RoleData roleData = roleDao.findByRoleName(role.getRoleName().toLowerCase(Locale.ROOT));
        if (!ObjectUtils.isEmpty(roleData)) {
            if ("INACTIVE".equals(roleData.getStatus())) {
                roleCreatedDto.setRoleName(roleData.getRoleName().toLowerCase(Locale.ROOT));
                roleCreatedDto.setMsg("Role Already Deleted!");
                roleCreatedDto.setRoleDescription(roleData.getDescription());
                return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.CONFLICT);

            }
            roleData.setStatus("INACTIVE");
            roleDao.save(roleData);
            roleCreatedDto.setRoleName(roleData.getRoleName());
            roleCreatedDto.setRoleDescription(roleData.getDescription());
            roleCreatedDto.setMsg("Role deleted successfully");
            LOGGER.info("role Deleted Successfully");
            return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.OK);

        }

        roleCreatedDto.setMsg("Role Does Not Exists");
        LOGGER.info("role Deletion not  Successfull");


        return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<RoleCreatedDto> update_role(RoleData role) {
        LOGGER.info("update_role method started");
        RoleCreatedDto roleCreatedDto = new RoleCreatedDto();
        RoleData roleData = roleDao.findByRoleName(role.getRoleName());
        if (!ObjectUtils.isEmpty(roleData)) {
            if ("INACTIVE".equals(roleData.getStatus())) {
                roleCreatedDto.setMsg("Role Already Deleted!");
                return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.CONFLICT);
            }
            roleData.setRoleName(role.getRoleName());
            roleData.setUpdatedBy(role.getUpdatedBy());
            roleData.setUpdatedDate(LocalDateTime.now());
            roleData.setDescription(role.getDescription());
            roleDao.save(roleData);
            roleCreatedDto.setMsg("Role Updated Successfully");
            roleCreatedDto.setRoleDescription(role.getDescription());
            roleCreatedDto.setRoleName(role.getRoleName());
            return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.OK);
        }

        roleCreatedDto.setMsg("Role Does Not Exists");
        roleCreatedDto.setRoleName(role.getRoleName());
        return new ResponseEntity<RoleCreatedDto>(roleCreatedDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<RoleAssignByAdminDto> assignRoleByAdmin(RoleAssignByAdminDto roleAssignByAdminDto) {
        LOGGER.info("assignRoleByAdmin method started");

        User user = userDao.findByUsernameOrEmail(roleAssignByAdminDto.getUname(), roleAssignByAdminDto.getUname());
        if (!ObjectUtils.isEmpty(user)) {
            RoleData roleData = roleDao.findByRoleName(roleAssignByAdminDto.getRoleName());
            if (!ObjectUtils.isEmpty(roleData)) {
                user.setRoleData(roleData);
                LOGGER.info(" Role Assigned Successfully");
                roleAssignByAdminDto.setMsg("Role" + roleAssignByAdminDto.getRoleName() + " Assigned Successfully to" + roleAssignByAdminDto.getUname());
                return new ResponseEntity<RoleAssignByAdminDto>(roleAssignByAdminDto, HttpStatus.OK);
            }
            LOGGER.info(" Role does not Exist!!");
            roleAssignByAdminDto.setMsg("Role " + roleAssignByAdminDto.getRoleName() + " does not Exist!");
            return new ResponseEntity<RoleAssignByAdminDto>(roleAssignByAdminDto, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("User Does Not Exists");
        roleAssignByAdminDto.setMsg("Username with " + roleAssignByAdminDto.getUname() + "does not exist");

        return new ResponseEntity<RoleAssignByAdminDto>(roleAssignByAdminDto, HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<?> add_department(DepartmentCreatedDto departmentCreatedDto) {

        LOGGER.info("Create dep method start");
        UserResponseDto userResponseDto = new UserResponseDto();
        if (!ObjectUtils.isEmpty(departmentCreatedDto)) {
            Department department = new Department();
            department.setCreatedBy(departmentCreatedDto.getCreatedBy());
            department.setDepartmentCode(departmentCreatedDto.getDepartmentCode());
            department.setDepartmentName(departmentCreatedDto.getDepartmentName());
            department.setDescription(departmentCreatedDto.getDescription());
            department.setStatus("ACTIVE");

            departmentDao.save(department);
            userResponseDto.setUname(departmentCreatedDto.getDepartmentName());
            userResponseDto.setMsg("Department is created");
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);

        }

        userResponseDto.setMsg("Empty fields");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
    }



    @Override
    public ResponseEntity<?> add_emp(EmployeeCreatedDto employeeCreatedDto) {

        UserResponseDto userResponseDto = new UserResponseDto();
        if (!ObjectUtils.isEmpty(employeeCreatedDto)) {
            Employee employee = new Employee();
            employee.setEmployeeCode(employeeCreatedDto.getEmployeeCode());
            employee.setFirstName(employeeCreatedDto.getFirstName());
            employee.setLastName(employeeCreatedDto.getLastName());
            employee.setEmail(employeeCreatedDto.getEmail());
            employee.setPhoneNumber(employeeCreatedDto.getPhoneNumber());
            employee.setCreatedBy(employeeCreatedDto.getCreatedBy());
            employee.setDateOfJoining(LocalDateTime.now());
            employee.setPassword(employeeCreatedDto.getPassword());
            employee.setStatus("ACTIVE");
            Department department=departmentDao.findByDepartmentId(employeeCreatedDto.getDepartmentId());
            if (!ObjectUtils.isEmpty(department)){
                employee.setDepartment(department);
            }
            Employee employee1=employeeDao.findByEmployeeCode(employeeCreatedDto.getEmployeeCode());
            if ((!ObjectUtils.isEmpty(employee))){
                employee.setManager(employee1);
            }


            employeeDao.save(employee);

            userResponseDto.setUname(employeeCreatedDto.getFirstName()+" "+employeeCreatedDto.getLastName());
            userResponseDto.setMsg("EMP CRested");
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
        }

userResponseDto.setMsg("MISSIING OR INVALID DATA");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
    }

    @Override
    public ResponseEntity<?> update_emp(EmployeeCreatedDto employeeCreatedDto) {

 UserResponseDto userResponseDto=new UserResponseDto();
        if (!ObjectUtils.isEmpty(employeeCreatedDto)){

         Employee employee= employeeDao.findByEmployeeCode((employeeCreatedDto.getEmployeeCode()));

         if (employee!=null) {

             if (StringUtils.hasText(employeeCreatedDto.getFirstName())) {
                 employee.setFirstName(employeeCreatedDto.getFirstName());
             }

             if (StringUtils.hasText(employeeCreatedDto.getLastName())) {
                 employee.setLastName(employeeCreatedDto.getLastName());
             }

             if (StringUtils.hasText(employeeCreatedDto.getEmail())) {
                 employee.setEmail(employeeCreatedDto.getEmail());
             }

             if (employeeCreatedDto.getPhoneNumber() != null) {
                 employee.setPhoneNumber(employeeCreatedDto.getPhoneNumber());
             }

             if (employeeCreatedDto.getDateOfJoining()!=null ) {
                 employee.setDateOfJoining((employeeCreatedDto.getDateOfJoining()));
             }

             if (employeeCreatedDto.getDateOfLeaving() != null) {
                 employee.setDateOfLeaving((employeeCreatedDto.getDateOfLeaving()));
             }

             if (StringUtils.hasText(employeeCreatedDto.getUpdatedBy())) {
                 employee.setUpdatedBy(employeeCreatedDto.getUpdatedBy());
             }
             if (employeeCreatedDto.getManagerId()!=0 || StringUtils
                     .hasText(employeeCreatedDto.getManagerCode())){
                    Employee employee1 = employeeDao.findByEmployeeCodeOrEmployeeId(employeeCreatedDto.getManagerCode(),employeeCreatedDto.getManagerId());
                    employee.setManager(employee1);
             }

             if (employeeCreatedDto.getDepartmentId()!=0){
                Department department=departmentDao.findByDepartmentId(Math.toIntExact(employeeCreatedDto.getDepartmentId()));
                employee.setDepartment(department);
             }


             userResponseDto.setMsg("Details Updated successfully! of "+employeeCreatedDto.getFirstName() );
             employeeDao.save(employee);
             return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
         }
         userResponseDto.setMsg("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userResponseDto);
         }

        userResponseDto.setMsg("No payload");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
    }

    @Override
    public ResponseEntity create_status(StatusMasterDto statusMasterDto) {
        if (statusMasterDto==null || !StringUtils.hasText(statusMasterDto.getStatus()) ){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no Status specified");
        }
        int statusId=UserRegIdGenerator.UserIdregGenerator();
        StatusMaster statusMaster= statusMasterDao.findBystatusId(statusId+"SM");

        if (statusMaster==null){
            StatusMaster statusMaster1=new StatusMaster();

            statusMaster1.setCreatedBy(statusMasterDto.getCreatedBy());
            statusMaster1.setStatusId("SM"+statusId);
            statusMaster1.setStatus(statusMasterDto.getStatus());
            statusMasterDao.save(statusMaster1);

            return ResponseEntity.status(HttpStatus.CREATED).body("Data is save in db");

        }
        return create_status(statusMasterDto);

    }

}





