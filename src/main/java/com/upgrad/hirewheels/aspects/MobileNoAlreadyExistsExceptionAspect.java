package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.MobileNoAlreadyExists;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MobileNoAlreadyExistsExceptionAspect {

    @ExceptionHandler(MobileNoAlreadyExists.class)
    public ResponseEntity<CustomResponse> handleMobileNoAlreadyExistsException(Exception e) {
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }
}