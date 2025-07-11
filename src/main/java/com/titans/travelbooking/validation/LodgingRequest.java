package com.titans.travelbooking.validation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingRequest {

    @NotBlank(message = "Hotel name must not be blank")
    @Size(min = 2, max = 100, message = "Hotel name must be between 2 and 100 characters")
    private String hotelName;

    @NotBlank(message = "Hotel type must not be blank")
    @Size(min = 2, max = 50, message = "Hotel type must be between 2 and 50 characters")
    private String hotelType;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Rating cannot be more than 5.0")
    private Float rating;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Available rooms count is required")
    @Min(value = 1, message = "There must be at least 1 room available")
    private Integer roomsAvailable;

    @NotBlank(message = "Country must not be blank")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String country;

    @NotBlank(message = "Image URL must not be blank")
    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    @Pattern(regexp = "^(https?|ftp)://.*$", message = "Image URL must be a valid URL")
    private String imageUrl;

    @NotBlank(message = "City must not be blank")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String city;

}
