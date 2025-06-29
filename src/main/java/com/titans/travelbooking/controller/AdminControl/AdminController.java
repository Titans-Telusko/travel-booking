package com.titans.travelbooking.controller.AdminControl;

import com.titans.travelbooking.validation.AdminRequest;
import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.service.adminService.IAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;


    @GetMapping
    public String getAdmin(){
        return "admin controller setup is ready";
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> saveAdmin(@RequestBody @Valid AdminRequest adminRequest){
        Admin admin=adminService.saveAdmin(adminRequest);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
}
