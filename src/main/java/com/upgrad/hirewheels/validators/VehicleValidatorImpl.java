package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.APIException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VehicleValidatorImpl implements VehicleValidator {

    @Override
    public void validateGetVehicles(LocalDateTime pickupDate, LocalDateTime dropOffDate, int locationId, String categoryName) throws APIException {
        if (locationId <= 0) {
            throw new APIException("Invalid location Id");
        }
        if (pickupDate == null) {
            throw new APIException("Invalid pickup date");
        }
        if (dropOffDate == null) {
            throw new APIException("Invalid dropoff date");
        }

        if (pickupDate.isAfter(dropOffDate)) {
            throw new APIException("Drop-off date should be greater than today's date and greater than the pickup date");
        }
        if (LocalDateTime.now().isAfter(pickupDate)) {
            throw new APIException("Pickup date should not be less than today's date");
        }
    }
}
