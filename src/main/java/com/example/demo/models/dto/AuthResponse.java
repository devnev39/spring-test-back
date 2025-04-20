package com.example.demo.models.dto;

import com.example.demo.models.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private User user;
    private String token; 
}
