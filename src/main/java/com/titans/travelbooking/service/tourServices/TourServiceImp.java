package com.titans.travelbooking.service.tourServices;


import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.entity.Tour;
import com.titans.travelbooking.exception.LocationNotFoundException;
import com.titans.travelbooking.exception.TourAlreadyExistsWithName;
import com.titans.travelbooking.exception.TourNotFoundException;
import com.titans.travelbooking.repository.ITourRepository;
import com.titans.travelbooking.repository.LocationRepo;
import com.titans.travelbooking.utils.TourMapper;
import com.titans.travelbooking.validation.TourRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TourServiceImp implements ITourService{

    private final ITourRepository tourRepository;
    private final LocationRepo locationRepository;

    public TourServiceImp(ITourRepository tourRepository, LocationRepo locationRepository){
        log.info("Entered into TourServiceImp constructor");
        this.tourRepository  = tourRepository;
        this.locationRepository = locationRepository;
        log.info("Injected the dependence objects {}, {}  into target object: {}",locationRepository.getClass(), tourRepository.getClass(), TourServiceImp.class);
    }

    @Override
    @Transactional
    public TourResponseDto createTour(TourRequest tourRequest) {
        log.info("Creating new tour with name: {}", tourRequest.getVacationName());
        // check that tour with same vacation name is there in the database or not.
        Optional<Tour> tour =
                tourRepository.findByVacationNameIgnoreCase(tourRequest.getVacationName());
        if(tour.isPresent()){
            throw new TourAlreadyExistsWithName("Tour exists with given vacation name :"+ tourRequest.getVacationName());
        }

        // find that locations are valid locations or not.
        Set<Location> locations = new HashSet<>();
        if (tourRequest.getLocationId() != null && !tourRequest.getLocationId().isEmpty()) {
            locations = tourRequest.getLocationId().stream()
                    .map(id -> locationRepository.findById(id)
                            .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + id)))
                    .collect(Collectors.toSet());
        }

        Tour newTour = buildTour(tourRequest, locations);
        log.info("Tour created successfully with name: {}", tourRequest.getVacationName());
        return TourMapper.toDto(newTour);

    }

    private Tour buildTour(TourRequest tourRequest, Set<Location> locations) {
        Tour newTour = new Tour();
        newTour.setLocations(locations); // set the locations for the new tour
        newTour.setVacationName(tourRequest.getVacationName());
        newTour.setStartDate(tourRequest.getStartDate());
        newTour.setEndDate(tourRequest.getEndDate());
        newTour.setPrice(tourRequest.getPrice());
        newTour.setNoOfCandidates(tourRequest.getNoOfCandidates());
        newTour.setTicketsAvailable(tourRequest.getNoOfCandidates()); // Initially, all tickets are available
        return newTour;
    }


    @Override
    @Transactional
    public TourResponseDto updateTour(TourRequest tourRequest, Integer id) {
        log.info("Updating tour with id: {}", id);
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            Tour updateTour = tour.get();
            if (tourRequest.getVacationName() != null && !tourRequest.getVacationName().trim().isEmpty()) {
                log.info("Updating vacation name to {}", tourRequest.getVacationName());
                updateTour.setVacationName(tourRequest.getVacationName());
            }
            if (tourRequest.getStartDate() != null) {
                log.info("Updating start date to {}", tourRequest.getStartDate());
                updateTour.setStartDate(tourRequest.getStartDate());
            }
            if (tourRequest.getEndDate() != null) {
                log.info("Updating end date to {}", tourRequest.getEndDate());
                updateTour.setEndDate(tourRequest.getEndDate());
            }
            if (tourRequest.getPrice() != null) {
                log.info("Updating price to {}", tourRequest.getPrice());
                updateTour.setPrice(tourRequest.getPrice());
            }
            if (tourRequest.getNoOfCandidates() != null) {
                log.info("Updating number of candidates to {}", tourRequest.getNoOfCandidates());
                updateTour.setNoOfCandidates(tourRequest.getNoOfCandidates());
                updateTour.setTicketsAvailable(tourRequest.getNoOfCandidates()); // update available tickets
            }
            if (tourRequest.getLocationId() != null && !tourRequest.getLocationId().isEmpty()) {
                log.info("Updating locations for tour");
                Set<Location> locations = tourRequest.getLocationId().stream()
                        .map(LocationId -> locationRepository.findById(LocationId)
                                .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + LocationId)))
                        .collect(Collectors.toSet());
                updateTour.setLocations(locations);
            }
            return TourMapper.toDto(updateTour);
        }
        log.info("Tour not found with id: {}", id);
        throw new TourNotFoundException("Tour not found with id: " + id);
    }

    @Override
    @Transactional
    public String deleteTour(Integer id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            tourRepository.delete(tour.get());
            return "Tour deleted successfully";
        }
        return "Not found with id: " + id;
    }

    @Override
    public List<TourResponseDto> getAllTours(Pageable pageable) {
        log.info("Fetching all tours at page: {}", pageable.getPageNumber());
        List<Tour> tours = tourRepository.findAll(pageable).getContent();
        if (!tours.isEmpty()) {
            log.info("Tours retrieved successfully");
            return tours.stream().map(tour -> TourMapper.toDto(tour)).collect(Collectors.toList());
        }
        log.info("No tours found");
        return new ArrayList<>(); // empty list
    }

    @Override
    public List<TourResponseDto> getAllAvailableTours() {
        log.info("Fetching all available tours which are available");
        List<Tour> tours = tourRepository.findByTicketsAvailableGreaterThan(0, PageRequest.of(0, 10));
        List<TourResponseDto> tourResponseDtos = new ArrayList<>();
        if (!tours.isEmpty()) {
            log.info("Available tours retrieved successfully");
            return tours.stream().map(tour -> TourMapper.toDto(tour)).collect(Collectors.toList());
        }
        log.info("No tours found with tickets available");
        return new ArrayList<>(); // empty list
    }

    @Override
    public TourResponseDto getTourByVacationName(String vacationName) {
        log.info("Fetching tour with name: {}", vacationName);
        Optional<Tour> tour = tourRepository.findByVacationNameIgnoreCase(vacationName);
        if (tour.isPresent()) {
            return TourMapper.toDto(tour.get());
        }
        log.info("Tour not found with name: {}", vacationName);
        throw new TourNotFoundException("Tour not found with name: " + vacationName);
    }
}
