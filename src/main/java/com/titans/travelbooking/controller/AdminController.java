package com.titans.travelbooking.controller;

import com.titans.travelbooking.dto.AdminRequest;
import com.titans.travelbooking.dto.UserLoginRequest;
import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.service.CustomAuthenticate;
import com.titans.travelbooking.service.adminService.IAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")

public class AdminController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private CustomAuthenticate authenticate;


    @GetMapping
    public String getAdmin() {
        return "admin controller setup is ready";
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> saveAdmin(@RequestBody @Valid AdminRequest adminRequest) {
        Admin admin = adminService.saveAdmin(adminRequest);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminAutenticate(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        System.out.println(userLoginRequest);

        String res = authenticate.userAuthentication(userLoginRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all-admins")
    public ResponseEntity<List<Admin>> findAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping("/greet")
    public String simpleGreet(){
        return "hello setup ready...";
    }

    @PutMapping("/update-admin/{id}")
    public ResponseEntity<String> updateAdmin(@RequestBody @Valid AdminRequest adminRequest, @PathVariable Integer id) {
        String res = adminService.updateAdminById(adminRequest, id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete-admin/ {id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
        return new ResponseEntity<>(adminService.deleteAdminById(id), HttpStatus.OK);
    }

    @PatchMapping("/update-password/{id}")
    public ResponseEntity<Admin> updatePassword(@PathVariable String password , @PathVariable Integer id){
        return new ResponseEntity<>(adminService.updatePasswordById(password,id),HttpStatus.OK);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<Admin>> searchByNameMatch(@PathVariable String word){
          return new ResponseEntity<>(adminService.searchByName(word),HttpStatus.OK);
    }


}
