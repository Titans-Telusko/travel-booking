package com.titans.travelbooking.dto;

import java.time.LocalDate;
import java.util.Set;

/**
 * Data Transfer Object (DTO) for representing a tour response.
 * This record is used to transfer tour data from the server to the client.
 */
public record TourResponseDto(
        /**
         * The unique identifier of the tour.
         */
        Integer tourId,
        /**
         * The name of the vacation package for the tour.
         */
        String vacationName,
        /**
         * The start date of the tour.
         */
        LocalDate startDate,
        /**
         * The end date of the tour.
         */
        LocalDate endDate,
        /**
         * The number of tickets available for the tour.
         */
        Integer ticketsAvailable,
        /**
         * A set of simplified location information (as LocationDto) included in the tour.
         */
        Set<LocationDto> locations
) {
    /**
     * Nested record representing simplified location information within a tour.
     */
    public record LocationDto(
            /**
             * The unique identifier of the location.
             */
            Integer locationId,
            /**
             * Details about the location (e.g., name, description).
             */
            String locationDetails
    ) {}
}
