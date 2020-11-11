package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.*;

public interface BookingService {

    Booking addBooking(Booking booking) throws UserNotRegisteredException, UnauthorizedUserException, InsufficientBalanceException, VehicleNotFoundException, APIException;
}
