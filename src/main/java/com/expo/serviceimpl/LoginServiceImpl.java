package com.expo.serviceimpl;

import com.expo.dao.EmployeeDao;
import com.expo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeDao employeeDao;

    // @Override
//    public ResponseEntity<?> login_emp(EmployeeCreatedDto employeeCreatedDto) {
//
//        UserResponseDto userResponseDto = new UserResponseDto();
//        if (!ObjectUtils.isEmpty(employeeCreatedDto)) {
//            Employee employee = employeeDao.findByEmployeeCode(employeeCreatedDto.getEmployeeCode());
//            if (employee == null) {
//                userResponseDto.setMsg("WRONG CODE!");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userResponseDto);
//            }
//            userResponseDto.setMsg("EMP LOGGED IN WELCOME" + employee.getFirstName());
//
//            return ResponseEntity.status(HttpStatus.FOUND).body(userResponseDto);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponseDto);
    //}

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (StringUtils.hasText(username)) {

            Employee employee = employeeDao.findByEmployeeCode(username);

            if (employee == null) {
                throw new UsernameNotFoundException(
                        "Employee not found with code: " + username
                );
            }

            return new User(
                    employee.getEmployeeCode(),
                    employee.getPassword(),
                    new ArrayList<>()
            );
        }

        throw new UsernameNotFoundException("Username cannot be empty");
    };
}