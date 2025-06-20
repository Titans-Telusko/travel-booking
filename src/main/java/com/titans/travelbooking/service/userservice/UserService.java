package com.titans.travelbooking.service.userservice;

import com.titans.travelbooking.validation.UserRequest;
import com.titans.travelbooking.entity.Users;
import com.titans.travelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public Users saveUser(UserRequest userRequest) {
          Users user=Users.builder()
                  .name(userRequest.getName())
                  .username(userRequest.getUsername())
                  .password(userRequest.getPassword())
                  .phone(userRequest.getPhone())
                  .age(userRequest.getAge())
                  .build();

          return userRepo.save(user);
    }
}
