package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.RoleDao;
import com.upgrad.hirewheels.dao.UserDao;
import com.upgrad.hirewheels.entities.Role;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.MobileNoAlreadyExists;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setupMockito() {

        // mocking roleDao to mimic role
        Mockito.when(roleDao.findById(1)).thenReturn(Optional.of(new Role("admin")));
        Mockito.when(roleDao.findById(2)).thenReturn(Optional.of(new Role("user")));


        User adminUser = new User("Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999998", 10000, roleDao.findById(1).get());

        // admin user already exists
        Mockito.when(userDao.findByEmail("upgrad@gmail.com")).thenReturn(Optional.of(adminUser));

        // mobile number already exists
        Mockito.when(userDao.findByMobileNo("9999999998")).thenReturn(Optional.of(adminUser));


        // create a new valid customer mimicking the email and mobile no doesn't exist in the db
        Mockito.when(userDao.findByMobileNo("9999999990")).thenReturn(Optional.empty());
        Mockito.when(userDao.findByEmail("customer@gmail.com")).thenReturn(Optional.empty());

        Mockito.when(userDao
                .save(new User("customer", "customer", "customer@123", "customer3@gmail.com",
                        "9999999990", 10000, roleDao.findById(2).get())))
                .thenReturn(new User(1, "customer", "customer", "customer@123", "customer3@gmail.com",
                        "9999999990", 10000, new Role("user")));

        // get user with invalid email
        Mockito.when(userDao.findByEmail("custom@gmail.com")).thenReturn(Optional.empty());

        // get user with Valid email
        Mockito.when(userDao.findByEmail("customer@gmail.com")).thenReturn(Optional.of(new User("customer", "customer", "customer@123", "customer@gmail.com",
                "9999999990", 10000, new Role("customer"))));
    }


    @Test
    public void testMobileNoAlreadyExistsGetUser() {
        User adminUser = new User("Upgrad", "Admin", "admin@123", "upgrad1@gmail.com",
                "9999999998", 10000, roleDao.findById(1).get());

        Exception exception = assertThrows(MobileNoAlreadyExists.class, () -> {
            User user = userService.createUser(adminUser);
        });

        String expectedMessage = "Mobile Number Already Exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testAlreadyExistsCreateUser() {
        User adminUser = new User("Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999998", 10000, roleDao.findById(1).get());

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            User user = userService.createUser(adminUser);
        });

        String expectedMessage = "Email Already Exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateUser() throws UserAlreadyExistsException, MobileNoAlreadyExists {
        User customer = new User("customer", "customer", "customer@123", "customer3@gmail.com",
                "9999999990", 10000, roleDao.findById(2).get());
        User savedCustomer = userService.createUser(customer);

        Assertions.assertNotNull(savedCustomer);
        Assertions.assertTrue(savedCustomer.getUserId() != 0);
        Assertions.assertEquals(savedCustomer.getEmail(), "customer3@gmail.com");
        Assertions.assertEquals(savedCustomer.getRole().getRoleName(), "user");
    }

    @Test
    public void testValidGetUser() throws UserNotRegisteredException, UnauthorizedUserException {
        User user = new User();
        user.setEmail("customer@gmail.com");
        user.setPassword("customer@123");
        user = userService.getUsers(user);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getEmail(), "customer@gmail.com");
        Assertions.assertEquals(user.getPassword(), "customer@123");
    }

    @Test
    public void testNotRegisteredGetUser() {
        Exception exception = assertThrows(UserNotRegisteredException.class, () -> {
            User user = new User();
            user.setEmail("custom@gmail.com");
            user.setPassword("customer@123");
            user = userService.getUsers(user);
        });

        Assertions.assertEquals(exception.getMessage(), "User not Registered");
    }

    @Test
    public void testUnauthorizedGetUser() {
        Exception exception = assertThrows(UnauthorizedUserException.class, ()-> {
            User user = new User();
            user.setEmail("customer@gmail.com");
            user.setPassword("customer@1234");
            user = userService.getUsers(user);
        });
        Assertions.assertEquals(exception.getMessage(), "Unauthorized User");

    }
}