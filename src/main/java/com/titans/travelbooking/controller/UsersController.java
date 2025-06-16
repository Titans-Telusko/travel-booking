package com.titans.travelbooking.controller;

import com.titans.travelbooking.entity.Users;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @PostMapping("/register")
    public ResponseEntity<Users> saveUser(@RequestBody @Valid Users user){
        System.out.println(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
