package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.VehicleNotFoundException;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class VehicleNotFoundExceptionAspect {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<CustomResponse> handleVehicleNotFoundException(Exception e) {
        CustomResponse response = new CustomResponse(LocalDateTime.now(), e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

    }
}
