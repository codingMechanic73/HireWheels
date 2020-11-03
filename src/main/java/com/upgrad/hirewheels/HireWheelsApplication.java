package com.upgrad.hirewheels;

import com.upgrad.hirewheels.dao.RoleDao;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.services.AdminService;
import com.upgrad.hirewheels.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HireWheelsApplication {

    public static void main(String[] args) throws UserAlreadyExistsException, MobileNoAlreadyExists, UserNotRegisteredException, UnauthorizedUserException, VehicleNotFoundException, VehicleAlreadyExistsException {
        ApplicationContext ctx = SpringApplication.run(HireWheelsApplication.class, args);

//        InitService initService = ctx.getBean(InitService.class);
//        initService.start();

        UserService userService = ctx.getBean(UserService.class);
        RoleDao roleDao = ctx.getBean(RoleDao.class);
        AdminService adminService = ctx.getBean(AdminService.class);


        // User with existing email
        User adminUser = new User("Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999998", 10000, roleDao.findById(1).get());
        userService.createUser(adminUser);

        // User with existing mobile
        adminUser = new User("bridgelabz", "Admin", "bridgelabz@1234", "upgrad@gmail.com",
                "9999999998", 10000, roleDao.findById(1).get());
        userService.createUser(adminUser);

        // get user with invalid email
        User user = new User();
        user.setEmail("1upgrad@gmail.com");
        user.setPassword("admin@123");
        User receivedUser = userService.createUser(user);
        System.out.println(receivedUser);

        // get user with wrong password
        user = new User();
        user.setEmail("upgrad@gmail1.com");
        user.setPassword("admin@12345");
        receivedUser = userService.getUsers(user);
        System.out.println(receivedUser);


    }

}
