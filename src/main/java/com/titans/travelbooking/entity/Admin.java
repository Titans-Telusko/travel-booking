package com.titans.travelbooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Admin {
    @Id
    @GeneratedValue(
            generator = "adminsequence",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "adminsequence",
            sequenceName = "adminsequence",
            initialValue = 5000,
            allocationSize = 1
    )
    private Integer aId;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true , nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
