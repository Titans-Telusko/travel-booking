package com.titans.travelbooking.service;

import com.titans.travelbooking.dto.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticate {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String userAuthentication(UserLoginRequest userLoginRequest){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return "login successfully";
        }
        else{
            return "invalid credintials please enter valid credintials";
        }
    }
}
