package com.upgrad.hirewheels;

import com.upgrad.hirewheels.dao.RoleDao;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HireWheelsApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) throws UserAlreadyExistsException, MobileNoAlreadyExists, UserNotRegisteredException, UnauthorizedUserException, VehicleNotFoundException, VehicleAlreadyExistsException {
        ApplicationContext ctx= SpringApplication.run(HireWheelsApplication.class, args);
        VehicleServiceImpl vehicleService = ctx.getBean(VehicleServiceImpl.class);

        System.out.println(vehicleService.getAllVehicles());
    }

}
