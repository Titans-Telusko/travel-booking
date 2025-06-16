package com.titans.travelbooking.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class userRequest {
    @NotNull
    private String name;
    @Email
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one number, and one special")
    private String password;
    @Pattern(regexp = "\\d{10}$")
    private Long phone;
    @Min(5)
    @Max(80)
    private Integer age;
}
