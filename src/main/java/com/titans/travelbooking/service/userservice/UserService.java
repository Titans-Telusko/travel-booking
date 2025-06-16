package com.titans.travelbooking.service.userservice;

import com.titans.travelbooking.entity.Users;
import com.titans.travelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public Users saveUser(Users user) {
          return userRepo.save(user);
    }
}
