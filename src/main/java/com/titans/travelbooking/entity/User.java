package com.titans.travelbooking.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User {

    private Long id;

    private String name;

}
