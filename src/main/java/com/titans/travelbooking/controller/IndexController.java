package com.titans.travelbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/welcome")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok("Welcome to Titans Tours and Travels");
    }

}
