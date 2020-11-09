package com.upgrad.hirewheels.Controllers;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.*;
import com.upgrad.hirewheels.services.VehicleService;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
public class VehicleController {


    @Autowired
    private EntityDTOConverter entityDTOConverter;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("hirewheels/v1/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(@RequestParam(name = "categoryName", required = false) String categoryName,
                                                           @RequestParam(name = "pickupDate", required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime pickupDate,
                                                           @RequestParam(name = "dropoffDate", required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime dropDate,
                                                           @RequestParam(name = "locationId", required = false) Integer locationId) {

        List<VehicleDTO> responseVehicleList = new ArrayList<>();
        if (StringUtils.isEmpty(categoryName) || pickupDate == null || dropDate == null || locationId == null) {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            vehicles.forEach(vehicle -> responseVehicleList.add(entityDTOConverter.convertToVehicleDTO(vehicle)));
        }
        else {
            Booking booking = new Booking();
            Location location = new Location();
            location.setLocationId(locationId);

            booking.setPickupDate(pickupDate);
            booking.setDropoffDate(dropDate);
            booking.setLocation(location);
            List<Vehicle> vehicles = vehicleService.getAvailableVehicles(categoryName, booking);
            vehicles.forEach(vehicle -> responseVehicleList.add(entityDTOConverter.convertToVehicleDTO(vehicle)));
        }


        return new ResponseEntity<>(responseVehicleList, HttpStatus.OK);
    }

}
