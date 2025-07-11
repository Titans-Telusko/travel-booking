package com.titans.travelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Lodging {
    @Id
    @Column(name="lodging_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lId;

    private String hotelName;

    private String hotelType;

    private Float rating;

    private BigDecimal price;

    private Integer roomsAvailable;

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
