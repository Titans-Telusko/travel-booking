package com.titans.travelbooking.controller.TourControl;

import com.titans.travelbooking.dto.CustomSingleSuccessResponse;
import com.titans.travelbooking.dto.CustomSuccessResponse;
import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.service.tourServices.ITourService;
import com.titans.travelbooking.validation.TourRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tours")
@Tag(   name = "Tour Controller",
        description = "This REST controller contains endpoint creating, updating, deleting and listing tours"
        +", In this Controller, only user with role ADMIN can create, update and delete tours."
)
public class TourController {

    private final ITourService tourService;

    @Autowired
    public TourController(ITourService tourService) {
        this.tourService = tourService;
        log.info("Injected the dependence object {} into target object: {}",
                tourService.getClass(), TourController.class);
    }

    @PostMapping("/create-tour")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new tour",
            description = "This endpoint is to create a new tour with the given request body. Only a user with ADMIN role can access this endpoint.")
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
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update tour",
            description = "This endpoint is to update the tour with the given id. Only a user with ADMIN role can access this endpoint.")
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
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete tour",
            description = "This endpoint is to delete the tour with the given id. Only a user with ADMIN role can access this endpoint.")
    public ResponseEntity<CustomSingleSuccessResponse<String>> deleteTour(@PathVariable Integer id) {
        log.info("Deleting tour with id: {}", id);
        String result = tourService.deleteTour(id);

        CustomSingleSuccessResponse<String> response =
                new CustomSingleSuccessResponse<>(HttpStatus.OK.value(),
                        "Tour deleted successfully", result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-tours")
    @Operation(summary = "Get all tours",
            description = "This endpoint is to list all the available tours.")
    public ResponseEntity<CustomSuccessResponse<TourResponseDto>> getAllTours(
            @PageableDefault(size = 5, page = 0, sort = "Price", direction = Sort.Direction.ASC) Pageable pageable) {

        log.info("Fetching all tours");
        List<TourResponseDto> tours = tourService.getAllTours(pageable);

        CustomSuccessResponse<TourResponseDto> response =
                new CustomSuccessResponse<>(HttpStatus.OK.value(),
                        "Tours retrieved successfully", tours);

        return ResponseEntity.ok(response);
    }

    @GetMapping("get-all-available-tours")
    @Operation(summary = "Get all available tours",
            description = "This endpoint is to list all the available tours.")
    public ResponseEntity<CustomSuccessResponse<TourResponseDto>> getAllAvailableTours() {
        log.info("Fetching all available tours");
        List<TourResponseDto> availableTours = tourService.getAllAvailableTours();

        CustomSuccessResponse<TourResponseDto> response =
                new CustomSuccessResponse<>(HttpStatus.OK.value(),
                        "Available tours retrieved successfully", availableTours);

        return ResponseEntity.ok(response);
    }

    @GetMapping("get-tour/{vacationName}")
    @Operation(summary = "Get tour by vacation name",
            description = "This endpoint is to get the tour by vacation name.")
    public ResponseEntity<CustomSingleSuccessResponse<TourResponseDto>> getTourByName(
            @PathVariable String vacationName) {
        log.info("Fetching tour with name: {}", vacationName);
        TourResponseDto tour = tourService.getTourByVacationName(vacationName);

        CustomSingleSuccessResponse<TourResponseDto> response =
                new CustomSingleSuccessResponse<>(HttpStatus.OK.value(),
                        "Tour retrieved successfully", tour);

        return ResponseEntity.ok(response);
    }
}