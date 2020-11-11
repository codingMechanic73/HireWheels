package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.APIException;

import java.time.LocalDateTime;

public interface VehicleValidator {
    void validateGetVehicles(LocalDateTime pickupDate, LocalDateTime dropOffDate, int locationId, String categoryName) throws APIException;
}
