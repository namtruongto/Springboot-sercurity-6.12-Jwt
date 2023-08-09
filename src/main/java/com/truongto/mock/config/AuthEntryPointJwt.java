package com.truongto.mock.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
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
        data.put("message", "Token hết hạn hoặc không hợp lệ");
        data.put("error", authException.getMessage());
        data.put("path", request.getRequestURI());
        data.put("timestamp", System.currentTimeMillis());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), data);

    }
    
}
