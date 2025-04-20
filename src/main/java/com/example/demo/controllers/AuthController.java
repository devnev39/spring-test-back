package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.common.UserPrincipal;
import com.example.demo.models.dto.AuthBody;
import com.example.demo.models.dto.AuthResponse;
import com.example.demo.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("")
    public AuthResponse login(@RequestBody AuthBody body) throws Exception {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword())
        );
        if (auth.isAuthenticated()) {
            UserPrincipal u = (UserPrincipal) auth.getPrincipal();
            return new AuthResponse(
                u.getUser(),
                jwtService.getToken(u.getUser())
            );
        }
        throw new Exception("Error occured !");
    }
}
