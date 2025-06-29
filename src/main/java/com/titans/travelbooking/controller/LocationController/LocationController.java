package com.titans.travelbooking.controller.LocationController;

import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.service.locationService.ILocationService;
import com.titans.travelbooking.validation.LocationRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/location")
public class LocationController {

    private final ILocationService locationService;

    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/add-location")
    public ResponseEntity<Location> addLocation(@RequestBody @Valid LocationRequest location) {

        try {
            log.info("Received request to add location: fromPlace={}, toPlace={}",
                    location.getFromPlace(), location.getToPlace());

            Location savedLocation = locationService.saveLocation(location);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating location: {}", e.getMessage());
            throw e;
        }
    }

}
