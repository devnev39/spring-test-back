package com.example.demo.services;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.demo.models.common.UserPrincipal;
import com.example.demo.models.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String jwtKey = "t6NNJrlp5X08gC4BKBs9MoMlKAWp2uxQ3qZU0Tjpm8Y=";

    private SecretKey getKey() {
        byte[] data = Base64.getDecoder().decode(jwtKey);
        return Keys.hmacShaKeyFor(data);
    }
    
    public String getToken(User u) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", u.getId());
        return Jwts.builder()
            .claims()
            .add(claims)
            .subject(u.getEmail())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 10*60*1000))
            .and()
            .signWith(getKey())
            .compact();
    } 

    public UserPrincipal verifyToken(String token) {
        Claims claims = Jwts.parser().verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        
        // get expiry
        if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
            throw new ExpiredJwtException(null, null, "JWT Expired!");
        }

        return new UserPrincipal(new User(claims.getSubject(), ((Number) claims.get("id")).longValue()));
    }
}
