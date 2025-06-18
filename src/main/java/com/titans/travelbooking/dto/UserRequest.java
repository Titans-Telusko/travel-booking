package com.titans.travelbooking.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "name field should not empty")
    private String name ;
    @Email(message = "enter valid email address")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one number, and one special")
    private String password;
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
    private Long phone;
    @Min(value = 5 , message = "age should be greater than 5")
    @Max(value = 80, message = "age should be less than 80")
    private Integer age;
}
