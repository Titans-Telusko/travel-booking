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
public class Lodging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lId;

    private String hotelName;

    private String hotelType;

    private Float rating;

    private BigDecimal price;

    private Integer roomsAvailabele;

    private String country;

    private String imageUrl;

    private String city;
    @OneToMany(
            mappedBy = "lodging",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
            @JsonIgnore
    List<Booking> bookings;
   @ManyToOne
   @JoinColumn(name = "lodging_id")
    private Location location;

}
