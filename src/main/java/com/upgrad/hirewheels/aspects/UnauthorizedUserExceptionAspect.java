package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UnauthorizedUserExceptionAspect {

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<CustomResponse> handleUnauthorizedUserException(Exception e) {
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

    }
}
