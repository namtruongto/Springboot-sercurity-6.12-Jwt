package com.truongto.mock.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private int status;
    private String message;
    private Object data;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
