package com.upgrad.hirewheels.services;


import com.upgrad.hirewheels.dao.VehicleCategoryDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.dao.VehicleSubcategoryDao;
import com.upgrad.hirewheels.entities.*;
import com.upgrad.hirewheels.exceptions.MobileNoAlreadyExists;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class VehicleServiceTest {

    @Mock
    private VehicleDao vehicleDao;

    @Mock
    private VehicleSubcategoryDao vehicleSubcategoryDao;

    @Mock
    private VehicleCategoryDao vehicleCategoryDao;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    Location location = null;

    @BeforeEach
    public void setupMockito() throws UserAlreadyExistsException, MobileNoAlreadyExists {
        location = new Location(1, "bantwal", "karnataka", new City(1, "mangalore"), "574219");
        Vehicle v = new Vehicle();
        v.setAvailabilityStatus(1);
        v.setLocation(location);

        List<Vehicle> vehicles = Arrays.asList(v, new Vehicle(), new Vehicle());

        Mockito.when(vehicleSubcategoryDao.findById(1)).thenReturn(Optional.of(new VehicleSubcategory()));

        Mockito.when(vehicleDao.findAll()).thenReturn(vehicles);

        Mockito.when(vehicleDao.findByUser(new User("Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999998", 10000, new Role()))).thenReturn(vehicles);

        Mockito.when(vehicleCategoryDao.findByVehicleCategoryName("booking")).thenReturn(new VehicleCategory());
    }


    @Test
    public void testAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        Assertions.assertNotNull(vehicles);
        Assertions.assertTrue(vehicles.size() != 0);
        Assertions.assertNotNull(vehicles.get(1));
    }

    @Test
    public void testVehiclesByUserName() {
        User user = new User("Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999998", 10000, new Role());
        List<Vehicle> vehicles = vehicleService.getVehicleByUserId(user);
        Assertions.assertNotNull(vehicles);
        Assertions.assertTrue(vehicles.size() != 0);
        Assertions.assertNotNull(vehicles.get(1));
    }

    @Test
    public void testAvailableVehicles() {
        Booking booking = new Booking();
        booking.setLocation(location);
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles("bike", booking);
        Assertions.assertNotNull(vehicles);
        Assertions.assertTrue(vehicles.size() != 0);
        Assertions.assertNotNull(vehicles.get(1));
    }




}
