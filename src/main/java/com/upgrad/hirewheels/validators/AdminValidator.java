package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.exceptions.APIException;

public interface AdminValidator {
    void validateAddVehicle(VehicleDTO vehicleDTO) throws APIException;

    void validChangeVehicleAvailability(int vehicleId, int availabilityStatus) throws APIException;
}
