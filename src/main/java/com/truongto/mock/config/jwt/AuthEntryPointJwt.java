package com.truongto.mock.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        data.put("message", authException.getMessage());
        data.put("error", authException.getClass().getName());
        data.put("path", request.getRequestURI());
        data.put("timestamp", System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), data);
    }
    
}
