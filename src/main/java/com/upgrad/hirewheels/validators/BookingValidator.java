package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.exceptions.APIException;

public interface BookingValidator {
    public void validateAddBooking(BookingDTO bookingDTO) throws APIException;
}
