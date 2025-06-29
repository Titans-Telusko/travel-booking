package com.titans.travelbooking.service.userservice;

import com.titans.travelbooking.validation.UserRequest;
import com.titans.travelbooking.entity.Users;
import org.apache.catalina.User;

import java.util.List;

public interface IUserService {

     Users saveUser(UserRequest userRequest);

     List<Users> getAllUsers();

     Users updateUsers(UserRequest userRequest , Integer id);

     String deleteById(Integer id);

     Users UpdateUserPassword(String password , Integer id);

     List<Users> searchByUser(String word);
}
