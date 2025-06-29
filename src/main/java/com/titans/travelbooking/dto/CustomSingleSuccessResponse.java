package com.titans.travelbooking.dto;

public record CustomSingleSuccessResponse<T>(int status, String message, T res) { }

