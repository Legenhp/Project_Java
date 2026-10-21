package com.expo.dto;


import lombok.Data;

@Data
public class DepartmentCreatedDto {

        private int dId;
        private String departmentCode;

        private String departmentName;

        private String description;

        private Integer departmentHeadId;

        private String status;
        private String createdBy;
        private String updatedBy;

}
