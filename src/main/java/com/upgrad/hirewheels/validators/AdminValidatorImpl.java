package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.exceptions.APIException;
import org.springframework.stereotype.Component;


/**
 * This is a validation class for input received by admin controller
 */
@Component
public class AdminValidatorImpl implements AdminValidator {


    /**
     * This method checks if the vehicle input given by user is valid or not
     * @param vehicleDTO
     * @throws APIException
     */
    @Override
    public void validateAddVehicle(VehicleDTO vehicleDTO) throws APIException {

        if (vehicleDTO.getVehicleImageUrl() == null
                || vehicleDTO.getVehicleImageUrl().isEmpty()
                || vehicleDTO.getVehicleModel() == null
                || vehicleDTO.getColor() == null
                || vehicleDTO.getVehicleNumber() == null
                || vehicleDTO.getColor().isEmpty() || vehicleDTO.getVehicleNumber().isEmpty() || vehicleDTO.getVehicleModel().isEmpty()) {
            throw new APIException("Fields shouldn’t be null or empty");
        }

        if (vehicleDTO.getVehicleSubCategoryId() <= 0) {
            throw new APIException("Invalid location Id");
        }
        if (vehicleDTO.getFuelTypeId() <= 0) {
            throw new APIException("Invalid fuel type Id");
        }
        if (vehicleDTO.getLocationId() <= 0) {
            throw new APIException("Invalid Location Id for vehicle");
        }
        if (!(vehicleDTO.getAvailabilityStatus() == 0 || vehicleDTO.getAvailabilityStatus() == 1)) {
            throw new APIException("Invalid status value");
        }
    }

    /**
     * This method checks if the status given is valid or not
     * @param vehicleId
     * @param availablityStatus
     * @throws APIException
     */
    @Override
    public void validChangeVehicleAvailability(int vehicleId, int availablityStatus) throws APIException {
        if (vehicleId <= 0) {
            throw new APIException("Invalid vehicle Id");
        }
        if (!(availablityStatus == 0 || availablityStatus == 1)) {
            throw new APIException("Invalid status");
        }
    }
}
