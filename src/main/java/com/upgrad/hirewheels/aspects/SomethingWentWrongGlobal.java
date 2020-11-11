//package com.upgrad.hirewheels.aspects;
//
//import com.upgrad.hirewheels.responses.CustomResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class SomethingWentWrongGlobal {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomResponse> handleFuelTypeDoesntExistsException(Exception e) {
//        CustomResponse response = new CustomResponse("Something went wrong", HttpStatus.NOT_FOUND.value());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//
//    }
//}
