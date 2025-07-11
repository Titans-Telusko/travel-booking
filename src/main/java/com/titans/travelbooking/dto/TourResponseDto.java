package com.titans.travelbooking.dto;

import java.time.LocalDate;
import java.util.Set;

public record TourResponseDto(
        Integer tourId,
        String vacationName,
        LocalDate startDate,
        LocalDate endDate,
        Integer ticketsAvailable,
        Set<LocationDto> locations
) {
    // Nested record for simplified location information
    public record LocationDto(  // By default, Nested records are static
            Integer locationId,
            String locationDetails
    ) {}
}
