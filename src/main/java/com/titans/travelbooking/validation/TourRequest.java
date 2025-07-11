package com.titans.travelbooking.validation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourRequest {

    @NotBlank(message = "Vacation name must not be blank")
    @Size(min = 3, max = 100, message = "Vacation name must be between 3 and 100 characters")
    private String vacationName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Number of candidates is required")
    @Min(value = 1, message = "Number of candidates must be at least 1")
    private Integer noOfCandidates;

    @NotNull(message = "Tickets available is required")
    @Min(value = 0, message = "Tickets available must be at least 0")
    private Integer ticketsAvailable;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotEmpty(message = "At least one location should be specified")
    @NotNull(message = "Location IDs are required")
    private Set<Integer> locationId;

}

