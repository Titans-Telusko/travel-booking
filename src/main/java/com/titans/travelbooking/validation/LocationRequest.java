package com.titans.travelbooking.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest {

    @NotBlank(message = "From Place name must not be blank")
    @Size(min = 2, max = 100, message = "From Place must be between 2 and 100 characters")
    private String fromPlace;

    @NotBlank(message = "To Place name must not be blank")
    @Size(min = 2, max = 100, message = "To Place must be between 2 and 100 characters")
    private String toPlace;

}
