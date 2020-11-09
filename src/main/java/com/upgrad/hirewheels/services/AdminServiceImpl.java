package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.exceptions.VehicleAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private UserService userService;

    public Vehicle registerVehicle(
            Vehicle vehicle)
            throws VehicleAlreadyExistsException {


        if (vehicleDao.findByVehicleNumber(vehicle.getVehicleNumber()).isPresent()) {
            throw new VehicleAlreadyExistsException("Vehicle Already exists");
        }
        vehicle.setAvailabilityStatus(1);
        return vehicleDao.save(vehicle);
    }


    @Override
    public Vehicle changeAvailability(
            int vehicleId,
            int status)
            throws UserNotRegisteredException, UnauthorizedUserException, VehicleNotFoundException {
        Vehicle updatedVehicle = vehicleDao.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        updatedVehicle.setAvailabilityStatus(status);
        return vehicleDao.save(updatedVehicle);

    }
}
