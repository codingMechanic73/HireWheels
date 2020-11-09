package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.BookingDao;
import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.InsufficientResourcesException;

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

    @Override
    public Booking addBooking(Booking booking) throws UserNotRegisteredException, UnauthorizedUserException, InsufficientBalanceException {
        User user  = userService.getUsers(booking.getUser());
        if (user.getWalletMoney() < booking.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        } else {
            Booking bookingMade = bookingDao.save(booking);
            user.setWalletMoney(user.getWalletMoney()-booking.getAmount());
            userDao.save(user);
            return bookingMade;
        }
    }
}
