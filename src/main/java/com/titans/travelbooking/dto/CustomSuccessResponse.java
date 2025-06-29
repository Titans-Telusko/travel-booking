package com.titans.travelbooking.dto;

import java.util.List;

public record CustomSuccessResponse<T>(int status, String message, List<T> res) {
}
