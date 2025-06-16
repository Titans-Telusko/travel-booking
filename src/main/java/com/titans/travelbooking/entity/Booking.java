package com.titans.travelbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(generator = "bookingsequence" ,strategy = GenerationType.SEQUENCE )
    @SequenceGenerator(
            name ="bookingsequence",
            sequenceName = "bookingsequence",
            initialValue = 2000,
            allocationSize = 1
    )
    private Integer bookingId;

    private LocalDate departure;

    private LocalDate arrival;

    private Integer NoOfDays;

    private Integer groupMembers;

    private BigDecimal totalPrice;

    private Boolean status;


    //relation colums
    @ManyToOne
    @JoinColumn( name = "customer_id" , nullable = false)
    private Users user;


    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
