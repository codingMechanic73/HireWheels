package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.*;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehicleByUserId(User user);

    List<Vehicle> getAvailableVehicles(VehicleSubcategory vehicleSubcategory, Booking booking);
}
