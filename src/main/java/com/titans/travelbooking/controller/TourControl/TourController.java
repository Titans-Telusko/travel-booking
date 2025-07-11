package com.titans.travelbooking.controller.TourControl;

import com.titans.travelbooking.dto.CustomSingleSuccessResponse;
import com.titans.travelbooking.dto.CustomSuccessResponse;
import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.service.tourServices.ITourService;
import com.titans.travelbooking.validation.TourRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tours")
public class TourController {

    private final ITourService tourService;

    @Autowired
    public TourController(ITourService tourService) {
        this.tourService = tourService;
        log.info("Injected the dependence object {} into target object: {}",
                tourService.getClass(), TourController.class);
    }

    @PostMapping("/create-tour")
    public ResponseEntity<CustomSingleSuccessResponse<TourResponseDto>> createTour(
            @Valid @RequestBody TourRequest tourRequest) {
        log.info("Creating new tour with name: {}", tourRequest.getVacationName());
        TourResponseDto createdTour = tourService.createTour(tourRequest);

        CustomSingleSuccessResponse<TourResponseDto> response =
                new CustomSingleSuccessResponse<>(HttpStatus.CREATED.value(),
                        "Tour created successfully", createdTour);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update-tour/{id}")
    public ResponseEntity<CustomSingleSuccessResponse<TourResponseDto>> updateTour(
            @PathVariable Integer id,
            @Valid @RequestBody TourRequest tourRequest) {
        log.info("Updating tour with id: {}", id);
        TourResponseDto updatedTour = tourService.updateTour(tourRequest, id);

        CustomSingleSuccessResponse<TourResponseDto> response =
                new CustomSingleSuccessResponse<>(HttpStatus.OK.value(),
                        "Tour updated successfully", updatedTour);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-tour/{id}")
    public ResponseEntity<CustomSingleSuccessResponse<String>> deleteTour(@PathVariable Integer id) {
        log.info("Deleting tour with id: {}", id);
        String result = tourService.deleteTour(id);

        CustomSingleSuccessResponse<String> response =
                new CustomSingleSuccessResponse<>(HttpStatus.OK.value(),
                        "Tour deleted successfully", result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-tours")
    public ResponseEntity<CustomSuccessResponse<TourResponseDto>> getAllTours() {
        log.info("Fetching all tours");
        List<TourResponseDto> tours = tourService.getAllTours();

        CustomSuccessResponse<TourResponseDto> response =
                new CustomSuccessResponse<>(HttpStatus.OK.value(),
                        "Tours retrieved successfully", tours);

        return ResponseEntity.ok(response);
    }

    @GetMapping("get-all-available-tours")
    public ResponseEntity<CustomSuccessResponse<TourResponseDto>> getAllAvailableTours() {
        log.info("Fetching all available tours");
        List<TourResponseDto> availableTours = tourService.getAllAvailableTours();

        CustomSuccessResponse<TourResponseDto> response =
                new CustomSuccessResponse<>(HttpStatus.OK.value(),
                        "Available tours retrieved successfully", availableTours);

        return ResponseEntity.ok(response);
    }

    @GetMapping("get-tour/{vacationName}")
    public ResponseEntity<CustomSingleSuccessResponse<TourResponseDto>> getTourByName(
            @PathVariable String vacationName) {
        log.info("Fetching tour with name: {}", vacationName);
        TourResponseDto tour = tourService.getTourById(vacationName);

        CustomSingleSuccessResponse<TourResponseDto> response =
                new CustomSingleSuccessResponse<>(HttpStatus.OK.value(),
                        "Tour retrieved successfully", tour);

        return ResponseEntity.ok(response);
    }
}