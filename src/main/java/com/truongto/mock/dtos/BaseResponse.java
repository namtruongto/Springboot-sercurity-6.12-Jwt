package com.truongto.mock.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public BaseResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
