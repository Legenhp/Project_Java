package com.expo.model;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwtToken;

    public JwtResponse (String tok){
        this.jwtToken=tok;
    }

}
