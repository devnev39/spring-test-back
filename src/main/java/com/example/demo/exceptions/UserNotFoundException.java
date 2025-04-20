package com.example.demo.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    } 
}
