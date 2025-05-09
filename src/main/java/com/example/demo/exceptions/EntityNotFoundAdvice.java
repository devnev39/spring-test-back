package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundAdvice {
    @ExceptionHandler({TaskNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> entityNotFoundExceptionHandler(EntityNotFoundException ex) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("message", ex.getMessage());
        return hm;
    } 
}
