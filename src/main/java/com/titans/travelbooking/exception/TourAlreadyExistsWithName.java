package com.titans.travelbooking.exception;

public class TourAlreadyExistsWithName extends RuntimeException {
    public TourAlreadyExistsWithName(String msg) {
        super(msg);
    }
}
