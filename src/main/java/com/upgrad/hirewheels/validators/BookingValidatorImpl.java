package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.exceptions.APIException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This is a validation class for booking controller class
 */
@Component
public class BookingValidatorImpl implements BookingValidator {

    /**
     * This method validates if the inputs for booking are valid or not
     * @param bookingDTO
     * @throws APIException
     */
    @Override
    public void validateAddBooking(BookingDTO bookingDTO) throws APIException {
        if (bookingDTO.getUserId() <= 0) {
            throw new APIException("Invalid user Id");
        }
        if (bookingDTO.getVehicleId() <= 0) {
            throw new APIException("Invalid vehicle Id");
        }
        if (bookingDTO.getLocationId() <= 0) {
            throw new APIException("Invalid location Id");
        }
        if (bookingDTO.getPickupDate() == null) {
            throw new APIException("Invalid pickup date");
        }
        if (bookingDTO.getDropoffDate() == null) {
            throw new APIException("Invalid dropoff date");
        }
        if (bookingDTO.getBookingDate() == null) {
            throw new APIException("Invalid booking date");
        }
        if (bookingDTO.getPickupDate().isAfter(bookingDTO.getDropoffDate())) {
            throw new APIException("Drop-off date should be greater than today's date and greater than the pickup date");
        }

        if (bookingDTO.getBookingDate().getYear() != LocalDateTime.now().getYear()
                || bookingDTO.getBookingDate().getMonth() != LocalDateTime.now().getMonth()
                || bookingDTO.getBookingDate().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
            throw new APIException("Booking date should be today's date");
        }

        if (bookingDTO.getBookingDate().isAfter(bookingDTO.getPickupDate())) {
            throw new APIException("Pickup date should not be less than today's date");
        }

    }
}
