package com.upgrad.hirewheels.services;


import com.upgrad.hirewheels.dao.VehicleCategoryDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    private VehicleCategoryDao vehicleCategoryDao;

    @Autowired

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }

    @Override
    public List<Vehicle> getVehicleByUserId(User user) {
        return vehicleDao.findByUser(user);
    }


    @Override
    public List<Vehicle> getAvailableVehicles(String vehicleCategoryName, Booking booking) {
        VehicleCategory vehicleCategory = vehicleCategoryDao.findByVehicleCategoryName(vehicleCategoryName);
        List<VehicleSubcategory> vehicleSubcategories = vehicleCategory.getVehicleSubcategories();

        List<Vehicle> vehicles = new ArrayList<>();

        for (VehicleSubcategory vehicleSubcategory: vehicleSubcategories) {
            vehicles.addAll(vehicleDao.findByVehicleSubcategory(vehicleSubcategory));
        }

        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAvailabilityStatus() == 1
                    && booking != null
                    && booking.getLocation() != null
                    && booking.getLocation().equals(vehicle.getLocation())) {
                List<Booking> vehicleBookings = vehicle.getBookings();
                boolean booked = false;
                for (Booking vb : vehicleBookings) {
                    if (!(booking.getPickupDate().before(vb.getPickupDate()) && booking.getDropoffDate().before(vb.getPickupDate())) ||
                            !(booking.getPickupDate().after(vb.getDropoffDate()) && booking.getDropoffDate().after(vb.getDropoffDate()))) {
                        booked = true;
                        break;
                    }
                }
                if (!booked) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }
}
