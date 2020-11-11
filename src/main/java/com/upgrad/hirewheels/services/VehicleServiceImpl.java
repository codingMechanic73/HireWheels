package com.upgrad.hirewheels.services;


import com.upgrad.hirewheels.dao.VehicleCategoryDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.*;
import com.upgrad.hirewheels.exceptions.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    private VehicleCategoryDao vehicleCategoryDao;

    @Autowired

    /**
     * This method get all the vehicles
     */
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }

    /**
     * This method gives all vehicles of certain owner
     *
     * @param user
     * @return
     */
    @Override
    public List<Vehicle> getVehicleByUserId(User user) {
        return vehicleDao.findByUser(user);
    }

    /**
     * This method gives all the available vehicles based on the category and booking criteria
     *
     * @param vehicleCategoryName
     * @param booking
     * @return
     */
    @Override
    public List<Vehicle> getAvailableVehicles(String vehicleCategoryName, Booking booking) {
        VehicleCategory vehicleCategory = vehicleCategoryDao.findByVehicleCategoryName(vehicleCategoryName);
        List<VehicleSubcategory> vehicleSubcategories = vehicleCategory.getVehicleSubcategories();

        List<Vehicle> vehicles = new ArrayList<>();

        for (VehicleSubcategory vehicleSubcategory : vehicleSubcategories) {
            List<Vehicle> vsc = vehicleDao.findByVehicleSubcategory(vehicleSubcategory);
            vehicles.addAll(vehicleDao.findByVehicleSubcategory(vehicleSubcategory));
        }

        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAvailabilityStatus() == 1
                    && booking.getLocation().getLocationId() == vehicle.getLocation().getLocationId()) {
                List<Booking> vehicleBookings = vehicle.getBookings();
                vehicleBookings.sort(Comparator.comparing(Booking::getPickupDate));
                LocalDateTime previousDropOff = LocalDateTime.now();
                LocalDateTime currentPickupDate;

                boolean isAvailable = false;
                for (Booking vb : vehicleBookings) {
                    currentPickupDate = vb.getPickupDate();
                    if (booking.getPickupDate().isAfter(previousDropOff) && booking.getDropoffDate().isBefore(currentPickupDate)) {
                        isAvailable = true;
                        break;
                    }
                    previousDropOff = vb.getDropoffDate();
                }
                if (booking.getPickupDate().isAfter(previousDropOff)) {
                    isAvailable = true;
                }
                if (isAvailable) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }
}
