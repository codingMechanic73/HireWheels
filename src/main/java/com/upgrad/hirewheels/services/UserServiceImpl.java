package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.MobileNoAlreadyExists;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) throws UserAlreadyExistsException, MobileNoAlreadyExists {
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }
        if (userDao.findByMobileNo(user.getMobileNo()).isPresent()) {
            throw new MobileNoAlreadyExists("Mobile Number Already Exists");
        }
        return userDao.save(user);
    }

    @Override
    public User getUsers(User user) throws UserNotRegisteredException, UnauthorizedUserException {
        User loggedUser = userDao.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotRegisteredException("User not Registered"));
        if (loggedUser.getPassword().equals(user.getPassword())) {
            return loggedUser;
        } else {
            throw new UnauthorizedUserException("Unauthorized User");
        }
    }

}
