package com.titans.travelbooking.exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String msg){
        super(msg);
    }
}
