package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.MobileNoAlreadyExists;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;

public interface UserService {
    User createUser(User user) throws UserAlreadyExistsException, MobileNoAlreadyExists;

    User getUsers(User user) throws UserNotRegisteredException, UnauthorizedUserException;
}
