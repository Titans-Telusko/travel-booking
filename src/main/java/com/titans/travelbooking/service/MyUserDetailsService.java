package com.titans.travelbooking.service;

import com.titans.travelbooking.entity.Admin;
import com.titans.travelbooking.entity.Users;
import com.titans.travelbooking.repository.IAdminRepo;
import com.titans.travelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
     @Autowired
    private UserRepository userRepo;

     @Autowired
     private IAdminRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        System.out.println(user);

        if(user != null){
            System.out.println("in authentication provider :" +user);
            return new UserPrincipal(user);
        }
        Admin admin=adminRepo.findByUsername(username);

        if(admin!=null){
            System.out.println("admin found from db : " +admin);

            return new UserPrincipal1(admin);
        }
        else
         throw new UsernameNotFoundException("user not found with username: "+ username);

    }
}
