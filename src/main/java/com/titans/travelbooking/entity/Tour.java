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
public class Tour {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer tourId;

      private String vacationName;

      private BigDecimal price;

      private Integer noOfCandidates;

      private Integer ticketsAvaiable;
      @OneToMany(
              mappedBy = "tour"
      )
              @JsonIgnore
      List<Booking> bookings;

}
