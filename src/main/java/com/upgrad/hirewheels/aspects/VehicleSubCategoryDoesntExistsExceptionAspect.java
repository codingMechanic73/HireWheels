package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.VehicleSubCategoryDoesntExistsException;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class VehicleSubCategoryDoesntExistsExceptionAspect extends Exception {
    @ExceptionHandler(VehicleSubCategoryDoesntExistsException.class)
    public ResponseEntity<CustomResponse> handleVehicleSubCategoryDoesntExistsExceptionException(Exception e) {
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
