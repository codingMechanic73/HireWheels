package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.BookingDao;
import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VehicleDao vehicleDao;

    /**
     * This method adds booking
     *
     * @param booking
     * @return
     * @throws UserNotRegisteredException
     * @throws UnauthorizedUserException
     * @throws InsufficientBalanceException
     */
    @Override
    public Booking addBooking(Booking booking) throws UserNotRegisteredException, UnauthorizedUserException, InsufficientBalanceException, VehicleNotFoundException, APIException {
        User user = userService.getUsers(booking.getUser());
        if (user.getWalletMoney() < booking.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        Vehicle vehicle = vehicleDao.findById(booking.getVehicle().getVehicleId()).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with id " + booking.getVehicle().getVehicleId()));
        if (vehicle.getLocation().getLocationId() != booking.getLocation().getLocationId()) {
            throw new APIException("Vehicle Id and Location Id are not matching");
        }
        if (vehicle.getAvailabilityStatus() == 0) {
            throw new APIException("Vehicle not available for booking");
        }
        List<Booking> vehicleBookings = vehicle.getBookings();
        vehicleBookings.sort(Comparator.comparing(Booking::getPickupDate));
        LocalDateTime previousDropOff = booking.getBookingDate();
        LocalDateTime currentPickupDate;

        boolean isAvailable = false;
        for (Booking vb: vehicleBookings) {
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
        if (!isAvailable) {
            throw new APIException("Vehicle not available between specified dates");
        }

        Booking bookingMade = bookingDao.save(booking);
        user.setWalletMoney(user.getWalletMoney() - booking.getAmount());
        userDao.save(user);
        return bookingMade;

    }
}
