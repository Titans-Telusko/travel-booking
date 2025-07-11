package com.titans.travelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.internal.build.AllowNonPortable;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllowNonPortable
@ToString
@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transportId;
    private String travelName;
    @Column(nullable = false)
    private String modeOfTransport;

    private LocalTime estimatedTime;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer ticketsAvailable;


    //relation columns
    @ManyToOne
    @JoinColumn(
            name = "location_id",
            nullable = false
    )
    private Location location;

    @OneToMany(
            mappedBy = "transport",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Booking> bookings;


}
