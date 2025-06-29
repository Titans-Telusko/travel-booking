package com.titans.travelbooking.service.userservice;

import com.titans.travelbooking.exception.UserNotFoundException;
import com.titans.travelbooking.validation.UserRequest;
import com.titans.travelbooking.entity.Users;
import com.titans.travelbooking.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepo;


    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Override
    public Users saveUser(UserRequest userRequest) {
          Users user=Users.builder()
                  .name(userRequest.getName())
                  .username(userRequest.getUsername())
                  .password(encoder.encode(userRequest.getPassword()))
                  .phone(userRequest.getPhone())
                  .age(userRequest.getAge())
                  .build();

          return userRepo.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Users updateUsers(UserRequest userRequest, Integer id) {
        return Users.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .password(encoder.encode(userRequest.getPassword()))
                .phone(userRequest.getPhone())
                .age(userRequest.getAge())
                .build();
    }

    @Override
    public String deleteById(Integer id) {
        Optional<Users> user = userRepo.findById(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return "user deleted with id : " + user.get().getUserId();
        }
        else {
            throw new UserNotFoundException("user not found with the id : "+ id);
        }
    }

    @Override
    public Users UpdateUserPassword(String password, Integer id) {
        Optional<Users> user = userRepo.findById(id);
        if(user.isPresent()) {
            user.get().setPassword(encoder.encode(password));
            return  user.get();
        }
        else throw new UserNotFoundException("user not found with id : " + id);
    }

    @Override
    public List<Users> searchByUser(String word) {
        return userRepo.searchByName(word);
    }
}
