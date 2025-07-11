package com.titans.travelbooking.service.tourServices;


import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.entity.Tour;
import com.titans.travelbooking.exception.TourAlreadyExistsWithName;
import com.titans.travelbooking.repository.ITourRepository;
import com.titans.travelbooking.repository.LocationRepo;
import com.titans.travelbooking.utils.TourMapper;
import com.titans.travelbooking.validation.TourRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourServiceImp implements ITourService{

    private final ITourRepository tourRepository;
    private final LocationRepo locationRepository;

    public TourServiceImp(ITourRepository tourRepository, LocationRepo locationRepository){
        this.tourRepository  = tourRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public TourResponseDto createTour(TourRequest tourRequest) {
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
                            .orElseThrow(() -> new RuntimeException("Location not found with id: " + id)))
                    .collect(Collectors.toSet());
        }

        Tour newTour = new Tour();
        newTour.setLocations(locations);
        newTour.setVacationName(tourRequest.getVacationName());
        newTour.setStartDate(tourRequest.getStartDate());
        newTour.setEndDate(tourRequest.getEndDate());
        newTour.setPrice(tourRequest.getPrice());
        newTour.setNoOfCandidates(tourRequest.getNoOfCandidates());
        newTour.setTicketsAvailable(tourRequest.getNoOfCandidates()); // Initially, all tickets are available

        return TourMapper.toDto(newTour);
    }


    @Override
    @Transactional
    public TourResponseDto updateTour(TourRequest tourRequest, Integer id) {
        return null;
    }

    @Override
    @Transactional
    public String deleteTour(Integer id) {
        return "";
    }

    @Override
    public List<TourResponseDto> getAllTours() {
        return List.of();
    }

    @Override
    public List<TourResponseDto> getAllAvailableTours() {
        return List.of();
    }

    @Override
    public TourResponseDto getTourById(String vacationName) {
        return null;
    }
}
