package com.truongto.mock.handlers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.truongto.mock.dtos.ErrorResponse;
import com.truongto.mock.dtos.ValidationErrorResponse;
import com.truongto.mock.thfw.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionsHandlers {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
                BindingResult bindingResult = ex.getBindingResult();
                String source = bindingResult.getObjectName();
                Object target = bindingResult.getTarget();
                if (target != null) source = target.getClass().getName();
                
                List<String> errors = bindingResult.getFieldErrors()
                                .stream()
                                .map(FieldError::getDefaultMessage)
                                .collect(Collectors.toList());
                ValidationErrorResponse errorResponse = new ValidationErrorResponse(source, "Validation failed", errors,
                                new Date());
                return errorResponse;
        }

        // SQLIntegrityConstraintViolationException
        @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse handleSQLIntegrityConstraintViolationException(
                        org.springframework.dao.DataIntegrityViolationException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMostSpecificCause().getMessage(),
                                HttpStatus.BAD_REQUEST.value(),
                                new Date());
                return errorResponse;
        }

        // NotFoundException
        @ExceptionHandler(NotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse handleNotFoundException(NotFoundException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(), HttpStatus.NOT_FOUND.value(),
                                new Date());
                return errorResponse;
        }

}
