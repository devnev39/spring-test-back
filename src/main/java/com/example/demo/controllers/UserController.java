package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.common.UserPrincipal;
import com.example.demo.models.entity.User;
import com.example.demo.repository.UserRepository;

@RestController()
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public User createUser(@RequestBody User entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setPassword(encoder.encode(entity.getPassword()));
        User u =  repository.save(entity);
        u.setPassword("");
        return u;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/self")
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        User user =  repository.findById(u.getUser().getId()).get();
        user.setPassword("");
        return user;
    }
}