package com.expo.serviceimpl;

import com.expo.model.JwtRequest;
import com.expo.model.JwtResponse;
import com.expo.service.AuthenticationService;
import com.expo.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtility jwtUtility;


    public JwtResponse authenticate_user(JwtRequest jwtRequest) throws Exception {


        Authentication authentication;
        try {


           authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid cerederntial", e);
        }

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
