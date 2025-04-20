package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class NotImplementedAdvice {
    @ExceptionHandler({NotImplementedException.class})
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    Map<String, String> notImplementedString(NotImplementedException ex) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("message", "Method not implemented !");
        return hm;
    }
}
