package com.titans.travelbooking.service.adminService;

import com.titans.travelbooking.dto.AdminRequest;
import com.titans.travelbooking.entity.Admin;

public interface IAdminService {
    Admin saveAdmin(AdminRequest adminRequest);
}
