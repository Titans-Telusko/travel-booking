package com.titans.travelbooking.controller.UserControl;

import com.titans.travelbooking.validation.UserRequest;
import com.titans.travelbooking.entity.Users;
import com.titans.travelbooking.service.userservice.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
     private IUserService userService;
    @PostMapping("/register")
    public ResponseEntity<Users> saveUser(@RequestBody @Valid UserRequest userRequest){
        System.out.println(userRequest);
        Users user=userService.saveUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/greet")
    public String greetMessage(){
        return "hello user controller setup is ready";
    }


    @GetMapping("/get-all-users")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<Users>> searchByUsername(@PathVariable String word){
        return new ResponseEntity<>(userService.searchByUser(word), HttpStatus.OK);
    }
}
