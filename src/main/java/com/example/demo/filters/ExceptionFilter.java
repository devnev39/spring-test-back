package com.example.demo.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws JsonProcessingException, IOException {
        Map<String, String> mp = new HashMap<>();
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            mp.put("error", "JWT Error !");
            mp.put("detail", ex.getMessage());
            response.setStatus(401);
            response.getWriter().write(convertObjectToJson(mp));
        } catch (Exception ex) {
            mp.put("error", "Internal server error");
            mp.put("detail", ex.getMessage());
            response.setStatus(500);
            response.getWriter().write(convertObjectToJson(mp));
        }
    }

    public String convertObjectToJson(Object obj) throws JsonProcessingException {
        if (obj == null) return null;
        ObjectMapper mp = new ObjectMapper();
        return mp.writeValueAsString(obj);
    }
    
}
