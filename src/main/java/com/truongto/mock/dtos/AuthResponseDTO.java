package com.truongto.mock.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AuthResponseDTO {

	private String username;

	private String email;

	private String tokenType = "Bearer";

	private String token;

	private List<String> roles;

	

	public AuthResponseDTO(String token) {
		this.token = token;
	}

	public AuthResponseDTO(String token, String username, String email,
			List<String> roles) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public AuthResponseDTO() {
	}



}
