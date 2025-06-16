package com.titans.travelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hotelName;

    private String hotelType;

    private Float rating;

    private BigDecimal price;

    private Integer roomsAvailabele;

    private String country;

    private String city;
    @OneToMany(
            mappedBy = "loging",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
            @JsonIgnore
    List<Booking> bookings;

}
