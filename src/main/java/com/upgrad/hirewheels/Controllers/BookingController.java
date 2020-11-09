package com.upgrad.hirewheels.Controllers;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.services.BookingService;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    private BookingService bookingService;

    @PostMapping("hirewheels/v1/booking")
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) throws UserNotRegisteredException, UnauthorizedUserException, InsufficientBalanceException {

        System.out.println(bookingDTO);
        Booking booking = entityDTOConverter.convertToBookingEntity(bookingDTO);
        Booking saveBooking = bookingService.addBooking(booking);
        BookingDTO responseBooking = entityDTOConverter.convertToBookingDTO(saveBooking);
        System.out.println(responseBooking);
        return new ResponseEntity<>(responseBooking, HttpStatus.CREATED);


    }
}
