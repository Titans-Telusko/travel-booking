package com.titans.travelbooking.service.adminService;

import com.titans.travelbooking.dto.AdminRequest;
import com.titans.travelbooking.entity.Admin;

import java.util.List;

public interface IAdminService {
    Admin saveAdmin(AdminRequest adminRequest);

    List<Admin> getAllAdmins();

    String updateAdminById(AdminRequest adminRequest,Integer id);

    String deleteAdminById(Integer id) ;


    Admin updatePasswordById(String password, Integer id) ;

    List<Admin> searchByName(String name);
}
