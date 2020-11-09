package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.exceptions.VehicleAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.VehicleNotFoundException;

public interface AdminService {

    Vehicle registerVehicle(Vehicle vehicle) throws UserNotRegisteredException, UnauthorizedUserException, VehicleAlreadyExistsException;

    public Vehicle changeAvailability(int vehicleId, int status) throws VehicleNotFoundException, UserNotRegisteredException, UnauthorizedUserException;
}
