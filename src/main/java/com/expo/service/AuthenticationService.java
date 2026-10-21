package com.expo.service;

import com.expo.model.JwtRequest;
import com.expo.model.JwtResponse;

import javax.validation.Valid;

public interface AuthenticationService {
    JwtResponse authenticate_user(@Valid JwtRequest jwtRequest) throws Exception;
}
