package com.expo.filter;

import com.expo.serviceimpl.LoginServiceImpl;
import com.expo.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private LoginServiceImpl loginServiceimpl;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {

            // Correct
            token = authorization.substring(7);

            userName = jwtUtility.getUserNameFromToken(token);

            if (userName != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        loginServiceimpl.loadUserByUsername(userName);

                if (jwtUtility.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken
                            usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    usernamePasswordAuthenticationToken
                            );
                }
            }
        }

        // IMPORTANT: always continue the request
        filterChain.doFilter(request, response);
    }
}