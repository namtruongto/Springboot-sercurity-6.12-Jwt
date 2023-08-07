package com.truongto.mock.dtos;

public class AuthResponseDTO {
    private String token;

	public AuthResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	public AuthResponseDTO() {
	}

}
