package com.upgrad.hirewheels.utils;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is used to convert DTO to Entity and vise versa
 */
@Component
public class EntityDTOConverter {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private VehicleSubcategoryDao vehicleSubcategoryDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private FuelTypeDao fuelTypeDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private BookingDao bookingDao;

    public VehicleDTO convertToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setVehicleModel(vehicle.getVehicleModel());
        vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
        vehicleDTO.setVehicleSubCategoryId(vehicle.getVehicleSubcategory().getVehicleSubcategoryId());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setLocationId(vehicle.getLocation().getLocationId());
        vehicleDTO.setFuelType(vehicle.getFuelType().getFuelType());
        vehicleDTO.setFuelTypeId(vehicle.getFuelType().getFuelTypeId());
        vehicleDTO.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDTO.setVehicleImageUrl(vehicle.getVehicleImageUrl());
        return vehicleDTO;
    }

    public Vehicle convertToVehicleEntity(VehicleDTO vehicleDTO) throws VehicleSubCategoryDoesntExistsException, LocationDoesntExistsException, FuelTypeDoesntExistsException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleDTO.getVehicleId());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleSubcategory(vehicleSubcategoryDao.findById(vehicleDTO.getVehicleSubCategoryId()).orElseThrow(()-> new VehicleSubCategoryDoesntExistsException("Vehicle subcategory not found with subcategory id " + vehicleDTO.getVehicleSubCategoryId())));
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setLocation(locationDao.findById(vehicleDTO.getLocationId()).orElseThrow(() -> new LocationDoesntExistsException("Location doesn't exist with location id " + vehicleDTO.getLocationId())));
        vehicle.setFuelType(fuelTypeDao.findById(vehicleDTO.getFuelTypeId()).orElseThrow(() -> new FuelTypeDoesntExistsException("Fuel Type doesn't exist with fuel id " + vehicleDTO.getFuelTypeId())));
        vehicle.setAvailabilityStatus(vehicleDTO.getAvailabilityStatus());
        vehicle.setVehicleImageUrl(vehicleDTO.getVehicleImageUrl());
        return vehicle;
    }

    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws UserNotRegisteredException, VehicleNotFoundException, LocationDoesntExistsException {
        Booking booking = new Booking();
        booking.setUser(userDao.findById(bookingDTO.getUserId()).orElseThrow(() -> new UserNotRegisteredException("User not registered with userId" + bookingDTO.getUserId())));
        booking.setVehicle(vehicleDao.findById(bookingDTO.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException("Vehicle doesn't exist with vehicle id " + bookingDTO.getVehicleId())));
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setDropoffDate(bookingDTO.getDropoffDate());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setLocation(locationDao.findById(bookingDTO.getLocationId()).orElseThrow(() -> new LocationDoesntExistsException("Location doesn't exist with location id " + bookingDTO.getLocationId())));
        booking.setAmount(bookingDTO.getAmount());
        return booking;
    }

    public BookingDTO convertToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId(booking.getUser().getUserId());
        bookingDTO.setVehicleId(booking.getVehicle().getVehicleId());
        bookingDTO.setPickupDate(booking.getPickupDate());
        bookingDTO.setDropoffDate(booking.getDropoffDate());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setLocationId(booking.getLocation().getLocationId());
        bookingDTO.setAmount(booking.getAmount());
        bookingDTO.setBookingId(booking.getBookingId());
        return bookingDTO;
    }

}
