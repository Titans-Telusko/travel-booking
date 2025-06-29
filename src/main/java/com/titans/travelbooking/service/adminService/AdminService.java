package com.titans.travelbooking.service.adminService;

import com.titans.travelbooking.validation.AdminRequest;
import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.exception.AdminNotFoundException;
import com.titans.travelbooking.repository.IAdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final IAdminRepo adminRepo;


    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Override
    public Admin saveAdmin(AdminRequest adminRequest) {
           return adminRepo.save(adminRequestToAdmin(adminRequest));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public String updateAdminById(AdminRequest adminRequest, Integer id){
        Optional<Admin> admin=adminRepo.findById(id);
        if(admin.isPresent()){
            Admin admin1=adminRepo.save(adminRequestToAdmin(adminRequest));
            return "updated the admin details having id : " + admin1.getAId();
        }
        throw  new AdminNotFoundException("admin details having id :" +id + " not found");
    }



    @Override
    public String deleteAdminById(Integer id)  {
        adminRepo.findById(id)
                .orElseThrow(()-> new AdminNotFoundException("admin having that id is not found : " +id));
        return "deleted admin having id: " + id;
    }

    @Override
    public Admin updatePasswordById(String password, Integer id)  {
//        Optional<Admin> admin=adminRepo.findById(id);
//        if(admin.isPresent()) {
//            admin.get().setPassword(encoder.encode(password));
//            return adminRepo.save(admin.get());
//        }
//        throw new AdminIdNotFoundException("admin is not found having id : " + id);
        Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("admin is not found having id : " + id));

        admin.setPassword(encoder.encode(password));
        return adminRepo.save(admin);

    }

    @Override
    public List<Admin> searchByName(String name) {
        return adminRepo.fetchByName(name);
    }

    private Admin adminRequestToAdmin(AdminRequest adminRequest){
           return  Admin.builder()
                   .fullName(adminRequest.getFullName())
                   .username(adminRequest.getUsername())
                   .password(encoder.encode(adminRequest.getPassword()))
                   .build();
    }
}
