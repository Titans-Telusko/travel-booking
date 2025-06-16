package com.titans.travelbooking.entity;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer touristId;

    private String fullName;

    private Integer age;

    private String gender;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
