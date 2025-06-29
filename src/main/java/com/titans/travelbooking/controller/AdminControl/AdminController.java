package com.titans.travelbooking.controller.AdminControl;

import com.titans.travelbooking.validation.AdminRequest;
import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.service.adminService.IAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all-admins")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        List<Admin>admins=adminService.getAllAdmins();
        return  new ResponseEntity<>(admins,HttpStatus.OK);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List> searchByUsername(@PathVariable String word){
        return new ResponseEntity<>(adminService.searchByName(word),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTheAdmin(@RequestBody AdminRequest adminRequest ,@PathVariable Integer id ){
        return  new ResponseEntity<>(adminService.updateAdminById(adminRequest, id), HttpStatus.OK);
    }

    @PatchMapping("/update-password/{password}/{id}")
    public ResponseEntity<Admin> updateAdminPassword(@PathVariable String password , @PathVariable Integer id){
        return  new ResponseEntity<>(adminService.updatePasswordById(password,id),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id){
        return  new ResponseEntity<>(adminService.deleteAdminById(id), HttpStatus.OK);
    }
}
