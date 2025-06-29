package com.titans.travelbooking.dto;

public record CustomErrorResponse(int status, String message, String error) { }
