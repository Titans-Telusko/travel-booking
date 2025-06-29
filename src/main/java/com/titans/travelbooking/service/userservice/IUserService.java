package com.titans.travelbooking.service.userservice;

import com.titans.travelbooking.validation.UserRequest;
import com.titans.travelbooking.entity.Users;

public interface IUserService {

     Users saveUser(UserRequest userRequest);
}
