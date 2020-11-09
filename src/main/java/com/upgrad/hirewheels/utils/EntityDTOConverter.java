package com.upgrad.hirewheels.utils;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.validation.ValidatorHandler;

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
        vehicleDTO.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDTO.setVehicleImageUrl(vehicle.getVehicleImageUrl());
        return vehicleDTO;
    }

    public Vehicle convertToVehicleEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleDTO.getVehicleId());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setVehicleSubcategory(vehicleSubcategoryDao.findById(vehicleDTO.getVehicleSubCategoryId()).get());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setLocation(locationDao.findById(vehicleDTO.getLocationId()).get());
        vehicle.setFuelType(fuelTypeDao.findById(vehicleDTO.getFuelTypeId()).get());
        vehicle.setAvailabilityStatus(vehicleDTO.getAvailabilityStatus());
        vehicle.setVehicleImageUrl(vehicleDTO.getVehicleImageUrl());
        return vehicle;
    }

    public Booking convertToBookingEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setUser(userDao.findById(bookingDTO.getUserId()).get());
        booking.setVehicle(vehicleDao.findById(bookingDTO.getVehicleId()).get());
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setDropoffDate(bookingDTO.getDropoffDate());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setLocation(locationDao.findById(bookingDTO.getLocationId()).get());
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
        bookingDTO.setAmount(bookingDTO.getAmount());
        bookingDTO.setBookingId(booking.getBookingId());
        return bookingDTO;
    }

}
