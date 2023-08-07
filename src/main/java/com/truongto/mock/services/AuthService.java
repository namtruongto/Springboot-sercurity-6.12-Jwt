package com.truongto.mock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truongto.mock.dtos.AuthResponseDTO;
import com.truongto.mock.entities.Person;
import com.truongto.mock.payload.AuthRequestDTO;
import com.truongto.mock.payload.RegisterRequestDTO;
import com.truongto.mock.thfw.enums.Role;
import com.truongto.mock.thfw.enums.Enums.Gender;

@Service
public class AuthService {
    
    @Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		Person person = new Person();
		person.setUsername(dto.getUsername());
		person.setEmail(dto.getEmail());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		person.setGender(Gender.MALE);
		person.addRole(Role.ADMIN);
		person = personService.create(person);
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}
	
	public AuthResponseDTO authenticate(AuthRequestDTO dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(), 
						dto.getPassword()));
		final Person person = personService.findByUserName(dto.getUsername());
		String token = jwtService.generateToken(person.getUsername());
		return new AuthResponseDTO(token);
	}
}
