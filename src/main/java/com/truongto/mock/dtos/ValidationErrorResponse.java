package com.truongto.mock.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private String source;
    private String message;
    private List<String> errors;
    private Date timestamp;
}
