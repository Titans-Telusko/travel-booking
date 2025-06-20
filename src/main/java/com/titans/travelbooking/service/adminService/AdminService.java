package com.titans.travelbooking.service.adminService;

import com.titans.travelbooking.validation.AdminRequest;
import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.repository.IAdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final IAdminRepo adminRepo;

    @Override
    public Admin saveAdmin(AdminRequest adminRequest) {
           return adminRepo.save(adminRequestToAdmin(adminRequest));
    }
    private Admin adminRequestToAdmin(AdminRequest adminRequest){
           return  Admin.builder()
                   .fullName(adminRequest.getFullName())
                   .username(adminRequest.getUsername())
                   .password(adminRequest.getPassword())
                   .build();
    }
}
