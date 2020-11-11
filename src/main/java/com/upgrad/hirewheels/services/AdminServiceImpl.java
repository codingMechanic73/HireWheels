package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
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


    /**
     * This method register vehicle if it is not registered already
     *
     * @param vehicle
     * @return
     * @throws VehicleAlreadyExistsException
     */
    public Vehicle registerVehicle(Vehicle vehicle) throws VehicleAlreadyExistsException {

        if (vehicleDao.findByVehicleNumber(vehicle.getVehicleNumber()).isPresent()) {
            throw new VehicleAlreadyExistsException("Vehicle Already exists with number " + vehicle.getVehicleNumber());
        }
        vehicle.setAvailabilityStatus(1);
        return vehicleDao.save(vehicle);
    }


    /**
     * This method toggles the availability status
     *
     * @param vehicleId
     * @param status
     * @return
     * @throws VehicleNotFoundException
     */
    @Override
    public Vehicle changeAvailability(
            int vehicleId,
            int status)
            throws VehicleNotFoundException {
        Vehicle updatedVehicle = vehicleDao.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for id " + vehicleId));
        updatedVehicle.setAvailabilityStatus(status);
        return vehicleDao.save(updatedVehicle);

    }
}
