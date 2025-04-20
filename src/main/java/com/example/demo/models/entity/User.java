package com.example.demo.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; 
    private String name;
    private String email;
    private String password;

    public User(String email, Long id) {
        this.email = email;
        this.id = id;
    }
}
