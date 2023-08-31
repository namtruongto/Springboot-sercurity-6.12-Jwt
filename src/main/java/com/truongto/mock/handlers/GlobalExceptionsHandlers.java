package com.truongto.mock.handlers;

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

        @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ValidationErrorResponse handleValidationException(org.springframework.web.bind.MethodArgumentNotValidException ex) {
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

        //jakarta.validation.ConstraintViolationException
        @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ValidationErrorResponse handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                List<String> errors = ex.getConstraintViolations()
                                .stream()
                                .map(violation -> violation.getMessage())
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

        // BadCredentialsException
        @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ErrorResponse handleBadCredentialsException(
                        org.springframework.security.authentication.BadCredentialsException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, "Invalid username or password",
                                HttpStatus.UNAUTHORIZED.value(), new Date());
                return errorResponse;
        }

        //Method not allowed exception
        @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
        public ErrorResponse handleMethodNotAllowedException(
                        org.springframework.web.HttpRequestMethodNotSupportedException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(),
                                HttpStatus.METHOD_NOT_ALLOWED.value(), new Date());
                return errorResponse;
        }

        // UnexpectedTypeException
        @ExceptionHandler(org.springframework.beans.TypeMismatchException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse handleUnexpectedTypeException(
                        org.springframework.beans.TypeMismatchException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(),
                                HttpStatus.BAD_REQUEST.value(), new Date());
                return errorResponse;
        }

        // org.springframework.http.converter.HttpMessageNotReadableException
        @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse handleHttpMessageNotReadableException(
                        org.springframework.http.converter.HttpMessageNotReadableException ex) {
                String source = ex.getClass().getSimpleName();
                String message = ex.getMessage();
                if(ex.getCause() != null) {
                        message = ex.getCause().getMessage();
                }
                ErrorResponse errorResponse = new ErrorResponse(source, message,
                                HttpStatus.BAD_REQUEST.value(), new Date());
                return errorResponse;
        }

        //Method not found exception
        @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse handleMethodNotFoundException(
                        org.springframework.web.servlet.NoHandlerFoundException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(),
                                HttpStatus.NOT_FOUND.value(), new Date());
                return errorResponse;
        }

        // InsufficientAuthenticationException (JWT)
        @ExceptionHandler(org.springframework.security.authentication.InsufficientAuthenticationException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ErrorResponse handleInsufficientAuthenticationException(
                        org.springframework.security.authentication.InsufficientAuthenticationException ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(),
                                HttpStatus.UNAUTHORIZED.value(), new Date());
                return errorResponse;
        }

        // Exception
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ErrorResponse handleException(Exception ex) {
                String source = ex.getStackTrace()[0].getClassName();
                ErrorResponse errorResponse = new ErrorResponse(source, ex.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());
                return errorResponse;
        }
        
}
