package com.truongto.mock.thfw.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Map<String, Object> data = new HashMap<>();
            data.put("status", HttpServletResponse.SC_FORBIDDEN);
            data.put("message", "Tài khoản không đủ quền truy cập tài nguyên này!");
            data.put("error", accessDeniedException.getMessage());
            data.put("path", request.getRequestURI());
            data.put("timestamp", System.currentTimeMillis());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), data);
    }
    
}
