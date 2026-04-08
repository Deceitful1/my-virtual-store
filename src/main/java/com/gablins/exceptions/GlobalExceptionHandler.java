package com.gablins.exceptions;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Bad request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(ProductNotFoundException ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<?> HandleInternalServerError(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<?> HandleForbiddenError(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> HandleAcessDeniedError(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsError(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }






    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> HandleBadRequest(Exception ex)
    {
        Error error = new Error(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

}
