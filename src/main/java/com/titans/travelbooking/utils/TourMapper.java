package com.titans.travelbooking.utils;

import com.titans.travelbooking.dto.TourResponseDto;
import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.entity.Tour;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper class for converting Tour entities to DTOs
 */
public class TourMapper {

    /**
     * Converts a Tour entity to a TourResponseDto
     */
    public static TourResponseDto toDto(Tour tour) {

        Set<TourResponseDto.LocationDto> locationDto = tour.getLocations().stream()
                .map(location -> TourMapper.toLocationDto(location))
                .collect(Collectors.toSet());

        return new TourResponseDto(
                tour.getTourId(),
                tour.getVacationName(),
                tour.getStartDate(),
                tour.getEndDate(),
                tour.getTicketsAvailable(),
                locationDto
        );
    }

    /**
     * Converts a Location entity to a LocationDto
     * */
    private static TourResponseDto.LocationDto toLocationDto(Location location) {
        return new TourResponseDto.LocationDto(
                location.getLocationId(),  // Using locationId field from Location entity
                location.getFromPlace() + " - " + location.getToPlace() // Creating locationDetails to get a path for the tour
        );
    }
}
