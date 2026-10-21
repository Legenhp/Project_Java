package com.expo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StatusUpdateDto {

    @NotBlank(message = "please enter loan id")
    private  String loanId;
    @NotNull(message = "please select a option")
    private Integer acceptReject;


}
