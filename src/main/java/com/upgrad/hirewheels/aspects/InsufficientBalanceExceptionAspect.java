package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.FuelTypeDoesntExistsException;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InsufficientBalanceExceptionAspect {
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomResponse> handleInsufficientBalanceException(Exception e) {
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

    }
}
