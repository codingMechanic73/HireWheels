package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class prefills the database with few values
 */
@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private RoleDao userRoleDAO;

    @Autowired
    private UserDao userDAO;

    @Autowired
    private VehicleCategoryDao vehicleCategoryDAO;

    @Autowired
    private VehicleSubcategoryDao vehicleSubCategoryDAO;

    @Autowired
    private CityDao cityDAO;

    @Autowired
    private FuelTypeDao fuelTypeDAO;

    @Autowired
    private LocationDao locationDAO;

    @Autowired
    private VehicleDao vehicleDao;


    public void start() {
        addUserRole();
        addUsers();
        addVehicleCategory();
        addVehicleSubCategory();
        addCity();
        addFuelType();
        addLocation();
        addVehicle();
    }

    private void addVehicle() {
        Vehicle vehicle = new Vehicle("1", "1234", vehicleSubCategoryDAO.findById(7).get(), "red", locationDAO.findById(14).get(), fuelTypeDAO.findById(12).get(), 1, "https://labmorghini.com/a.jpg");
        vehicleDao.save(vehicle);
    }

    private void addLocation() {
        Location location = new Location(1, "Worli",
                "Dr E Moses Rd, Worli Naka, Upper Worli", cityDAO.findById(11).get(), "400018");
        locationDAO.save(location);
        location = new Location(2, "Chembur",
                "Optic Complex", cityDAO.findById(11).get(), "400019");
        locationDAO.save(location);
        location = new Location(3, "Powai",
                "Hiranandani Tower", cityDAO.findById(11).get(), "400020");
        locationDAO.save(location);
    }

    private void addFuelType() {
        List<FuelType> fuelTypeList = Arrays.asList(new FuelType(1, "Petrol"), new FuelType(2, "Diesel"));
        fuelTypeDAO.saveAll(fuelTypeList);
    }

    private void addCity() {
        cityDAO.save(new City(11, "Mumbai"));
    }

    private void addVehicleCategory() {
        List<VehicleCategory> vehicleCategoryList = Arrays.asList(new VehicleCategory(2, "CAR"),
                new VehicleCategory(1, "BIKE"));
        vehicleCategoryDAO.saveAll(vehicleCategoryList);
    }

    private void addVehicleSubCategory() {
        List<VehicleSubcategory> vehicleSubCategories = new ArrayList<>();

        vehicleSubCategories.add(new VehicleSubcategory(1, "SUV",
                300, vehicleCategoryDAO.findById(4).get()));

        vehicleSubCategories.add(new VehicleSubcategory(2, "SEDAN",
                350, vehicleCategoryDAO.findById(4).get()));

        vehicleSubCategories.add(new VehicleSubcategory(3, "HATCHBACK",
                250, vehicleCategoryDAO.findById(4).get()));

        vehicleSubCategories.add(new VehicleSubcategory(4, "CRUISER",
                200, vehicleCategoryDAO.findById(5).get()));

        vehicleSubCategories.add(new VehicleSubcategory(5, "DIRT BIKE",
                200, vehicleCategoryDAO.findById(5).get()));

        vehicleSubCategories.add(new VehicleSubcategory(6, "SPORTS BIKE",
                150, vehicleCategoryDAO.findById(5).get()));

        vehicleSubCategoryDAO.saveAll(vehicleSubCategories);
    }

    private void addUserRole() {

        List<Role> userRoleList = Arrays.asList(new Role(1, "Admin"),
                new Role(2, "User"));
        userRoleDAO.saveAll(userRoleList);
    }


    private void addUsers() {
        User adminUser = new User(1,"Upgrad", "Admin", "admin@123", "upgrad@gmail.com",
                "9999999999", 10000, userRoleDAO.findById(1).get());
        userDAO.save(adminUser);
    }

}

