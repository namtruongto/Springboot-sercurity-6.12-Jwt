package com.truongto.mock.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequestDTO {

    @NotBlank(message = "Username là bắt buộc")
    @Size(min = 3, max = 20, message = "Username phải có độ dài từ 3 đến 20 ký tự")
    private String username;

    @NotBlank(message = "Password là bắt buộc")
    @Size(min = 6, max = 40, message = "Password phải có độ dài từ 6 đến 40 ký tự")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
