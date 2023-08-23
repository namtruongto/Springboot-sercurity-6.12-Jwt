package com.truongto.mock.controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truongto.mock.config.jwt.JwtService;
import com.truongto.mock.config.security.AuthService;
import com.truongto.mock.dtos.AuthResponseDTO;
import com.truongto.mock.payload.AuthRequestDTO;
import com.truongto.mock.payload.RegisterRequestDTO;


import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthRequestDTO dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}

	@PostMapping("/dich")
	public ResponseEntity<?> dich(@RequestBody AuthResponseDTO dto) {
		Claims cl;
		try {
			cl = jwtService.getClaims(dto.getToken());
			return ResponseEntity.ok(cl);
		} catch (Exception e) {
			return ResponseEntity.ok(new HashMap<String, Object>() {
				{
					put("error", e.getMessage());
				}
			});
		}

	}
}
