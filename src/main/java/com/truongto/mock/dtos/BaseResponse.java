package com.truongto.mock.dtos;

import com.truongto.mock.thfw.enums.Enums.Status;

import lombok.Data;

@Data
public class BaseResponse {
    private Status status;
    private String message;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(Status status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
