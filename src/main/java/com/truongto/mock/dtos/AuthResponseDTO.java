package com.truongto.mock.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
	private String username;
	private String email;
	private List<String> roles;

	public AuthResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	public AuthResponseDTO() {
	}

	public AuthResponseDTO(String token, String username, String email, List<String> roles) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

}
