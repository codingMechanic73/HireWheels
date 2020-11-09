package com.upgrad.hirewheels.Controllers;

import com.upgrad.hirewheels.dto.SuccessDTO;
import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.UnauthorizedUserException;
import com.upgrad.hirewheels.exceptions.UserNotRegisteredException;
import com.upgrad.hirewheels.exceptions.VehicleAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.VehicleNotFoundException;
import com.upgrad.hirewheels.services.AdminService;
import com.upgrad.hirewheels.services.VehicleService;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class AdminController {

    @Autowired
    private EntityDTOConverter entityDTOConverter;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AdminService adminService;

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("hirewheels/v1/vehicles")
    public ResponseEntity<SuccessDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) throws UserNotRegisteredException, UnauthorizedUserException, VehicleAlreadyExistsException {
        logger.debug("Adding new Vehicle", vehicleDTO);
        entityDTOConverter.convertToVehicleEntity(vehicleDTO);
        adminService.registerVehicle(entityDTOConverter.convertToVehicleEntity(vehicleDTO));
        System.out.println(vehicleDTO);
        SuccessDTO reponse = new SuccessDTO();
        reponse.timestamp = new Date();
        reponse.message = "Vehicle Added Successfully";
        reponse.statusCode = 200;
        logger.info("new Vehicle was added successfully");
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @PutMapping("hirewheels/v1/vehicles/{id}")
    public ResponseEntity<SuccessDTO> updateStatus(@PathVariable("id") int id, @RequestParam("availabilityStatus") int availabilityStatus) throws UserNotRegisteredException, UnauthorizedUserException, VehicleAlreadyExistsException, VehicleNotFoundException {
        logger.debug("updating status for vehicle " + id);
        adminService.changeAvailability(id, availabilityStatus);
        SuccessDTO reponse = new SuccessDTO();
        reponse.timestamp = new Date();
        reponse.message = "Vehicle Added Successfully";
        reponse.statusCode = 200;
        logger.info("status for vehicle " + id + " updated successfully");
        return new ResponseEntity<>(reponse, HttpStatus.ACCEPTED);
    }

}
