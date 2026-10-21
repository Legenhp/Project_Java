package com.expo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtRequest {

    @NotBlank(message = "Enter username!")
    private String username;
    @NotBlank(message = "Enter password!")
    private  String password;
}
