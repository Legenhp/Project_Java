package com.expo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class EmployeeCreatedDto {

        @NotBlank(message = "Enter code !!")
        private String employeeCode;

        private String firstName;

        private String lastName;

        private String password;
        private String email;

        private Long phoneNumber;

        private String createdBy;

        private String updatedBy;

        private String status;

        private int departmentId;

        private String managerCode;
        private int managerId;

        private LocalDateTime dateOfJoining;
        private LocalDateTime dateOfLeaving;

}
