package com.titans.travelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Users {
    @Id
    @GeneratedValue(
            generator = "usersequence",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "usersequence",
            sequenceName = "usersequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Integer userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false , unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long phone;
    private Integer age;



    // relation columns
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Person> personList;
}
