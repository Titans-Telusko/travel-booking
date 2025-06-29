package com.titans.travelbooking.exception;

public class LodgeNotFoundException extends RuntimeException {
    public LodgeNotFoundException(String message) {
        super(message);
    }
}
