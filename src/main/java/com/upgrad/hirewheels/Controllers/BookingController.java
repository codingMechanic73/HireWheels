package com.upgrad.hirewheels.Controllers;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.services.BookingService;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping("hirewheels/v1/booking")
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) throws UserNotRegisteredException, UnauthorizedUserException, InsufficientBalanceException {
        logger.debug("adding new booking for vehicle", bookingDTO);
        System.out.println(bookingDTO);
        Booking booking = entityDTOConverter.convertToBookingEntity(bookingDTO);
        Booking saveBooking = bookingService.addBooking(booking);
        BookingDTO responseBooking = entityDTOConverter.convertToBookingDTO(saveBooking);
        logger.info("booking was added successfully with booking id " + saveBooking.getBookingId());
        return new ResponseEntity<>(responseBooking, HttpStatus.CREATED);


    }
}
