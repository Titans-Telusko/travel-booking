package com.titans.travelbooking.dto;

import java.math.BigDecimal;

public record LodgingPatchRequest(
        String hotelName,
        String hotelType,
        Float rating,
        BigDecimal price,
        Integer roomsAvailabele,
        String country,
        String imageUrl,
        String city
) { }
