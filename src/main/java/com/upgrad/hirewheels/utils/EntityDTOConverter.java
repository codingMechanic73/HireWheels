package com.upgrad.hirewheels.utils;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;

public class EntityDTOConverter {

    public static VehicleDTO convertToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setVehicleModel(vehicle.getVehicleModel());
        vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
        vehicleDTO.setVehicleSubcategoryId(vehicle.getVehicleSubcategory().getVehicleSubcategoryId());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setLocationId(vehicle.getLocation().getLocationId());
        vehicleDTO.setFuelType(vehicle.getFuelType().getFuelType());
        vehicleDTO.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDTO.setVehicleImageUrl(vehicle.getVehicleImageUrl());
        return vehicleDTO;
    }
}
