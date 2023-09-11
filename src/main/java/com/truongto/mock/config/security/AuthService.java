package com.truongto.mock.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truongto.mock.config.jwt.JwtService;
import com.truongto.mock.dtos.AuthResponseDTO;
import com.truongto.mock.entities.User;
import com.truongto.mock.payload.AuthRequestDTO;
import com.truongto.mock.payload.RegisterRequestDTO;
import com.truongto.mock.services.UserService;
import com.truongto.mock.thfw.enums.Role;
import com.truongto.mock.thfw.enums.Enums.Gender;

@Service
public class AuthService {
    
    @Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		User person = new User();
		person.setUsername(dto.getUsername());
		person.setEmail(dto.getEmail());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		person.setGender(Gender.MALE);
		person.addRole(Role.ADMIN);
		person = userService.create(person);
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()), person.getUsername(), person.getEmail(), person.getRolesString());
	}
	
	public AuthResponseDTO authenticate(AuthRequestDTO dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(), 
						dto.getPassword()));
		final User person = userService.findByUserName(dto.getUsername());
		String token = jwtService.generateToken(person.getUsername());
		return new AuthResponseDTO(token, person.getUsername(),person.getEmail(), person.getRolesString());
	}

	// refresh token
}
