package com.titans.travelbooking.service.tourServices;


import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.validation.TourRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface ITourService {

    TourResponseDto createTour(@Valid TourRequest tourRequest);

    TourResponseDto updateTour(@Valid TourRequest tourRequest, Integer id);

    String deleteTour(Integer id);

    List<TourResponseDto> getAllTours();

    List<TourResponseDto> getAllAvailableTours();

    TourResponseDto getTourById(String vacationName);
}
