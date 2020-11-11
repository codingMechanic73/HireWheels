package com.upgrad.hirewheels.Controllers;

import com.upgrad.hirewheels.dto.SuccessDTO;
import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.services.AdminService;
import com.upgrad.hirewheels.services.VehicleService;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import com.upgrad.hirewheels.validators.AdminValidator;
import com.upgrad.hirewheels.validators.BookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class AdminController {

    @Autowired
    private EntityDTOConverter entityDTOConverter;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookingValidator bookingValidator;

    @Autowired
    private AdminValidator adminValidator;

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping(value = "hirewheels/v1/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) throws UserNotRegisteredException, UnauthorizedUserException, VehicleAlreadyExistsException, APIException, VehicleSubCategoryDoesntExistsException, FuelTypeDoesntExistsException, LocationDoesntExistsException {
        logger.debug("Adding new Vehicle", vehicleDTO);
        adminValidator.validateAddVehicle(vehicleDTO);
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
    public ResponseEntity<SuccessDTO> updateStatus(@PathVariable("id") int id, @RequestParam("availabilityStatus") int availabilityStatus) throws UserNotRegisteredException, UnauthorizedUserException, VehicleAlreadyExistsException, VehicleNotFoundException, APIException {
        logger.debug("updating status for vehicle " + id);
        adminValidator.validChangeVehicleAvailability(id, availabilityStatus);
        adminService.changeAvailability(id, availabilityStatus);
        SuccessDTO reponse = new SuccessDTO();
        reponse.timestamp = new Date();
        reponse.message = "Status Updated Successfully";
        reponse.statusCode = 200;
        logger.info("status for vehicle " + id + " updated successfully");
        return new ResponseEntity<>(reponse, HttpStatus.ACCEPTED);
    }

}
