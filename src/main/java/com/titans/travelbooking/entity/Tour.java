package com.titans.travelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name="tour_id")
      private Integer tourId;

      @Column(nullable = false)
      private String vacationName;

      @Column(nullable = false)
      @Temporal(TemporalType.DATE)
      private LocalDate startDate;

      @Column(nullable = false)
      @Temporal(TemporalType.DATE)
      private LocalDate endDate;

      @Column(nullable = false)
      private BigDecimal price;

      @Column(nullable = false)
      private Integer noOfCandidates;

      @Column(nullable = false)
      private Integer ticketsAvailable;

      @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
      @JoinTable(
              name = "tour_location", // Creates a junction table named "tour_location" to implement the many-to-many relationship between Tour and Location entities
              joinColumns = @JoinColumn(name = "tour_id"), // The foreign key column in the junction table that references the Tour entity (this side of the relationship)
              inverseJoinColumns = @JoinColumn(name = "location_id") // The foreign key column in the junction table that references the Location entity (the other side of the relationship)
      )
      private Set<Location> locations = new HashSet<>();

      @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
      @JsonIgnore
      private List<Booking> bookings = new ArrayList<>();


}

