package com.expo.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
public class StatusMasterDto {


    private String statusId;
    @NotBlank(message = "Createdby  by is empty")
    private String createdBy;

    @NotBlank(message = "updated  by is empty")
    private String updatedBy;

    @NotBlank(message = "Status is empty")
    private String status;

}
