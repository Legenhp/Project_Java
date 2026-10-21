package com.expo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {

private static final long JWT_VALIDITY=30*60;

    @Value("secretKey123")
    private String secretKey;

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    ;

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    };

    private Boolean isTokenExpired(String token){
        Date expire=getExpirationDateFromToken(token);
        return expire.before(new Date());
    };

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);


    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey("secretKey123").parseClaimsJws(token).getBody();
    }
    public String generateToken(UserDetails userDetails){
        Map<String , Object> claims =new HashMap<String , Object>();
        return doGenerateToken(claims,userDetails.getUsername());


    };

    public String doGenerateToken(Map<String , Object> claims ,String subject){
      return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000))
              .signWith(SignatureAlgorithm.HS512, secretKey).compact();

    };

    public Boolean validateToken(String token,UserDetails userDetails){
      String username = getUserNameFromToken(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    };


}
