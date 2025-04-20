package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.NotImplementedException;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("")
    public String getString() {
        return "Hello world !";
    }

    @GetMapping("/resp")
    public ResponseEntity<String> getResponseEntity() {
        return new ResponseEntity<>("Created !", HttpStatus.CREATED);
    }

    @GetMapping("/exc")
    public void getException() {
        throw new NotImplementedException("Not implemented!");
    }
}
