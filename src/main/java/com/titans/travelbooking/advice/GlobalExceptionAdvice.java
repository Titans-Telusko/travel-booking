package com.titans.travelbooking.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.titans.travelbooking.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.titans.travelbooking.dto.CustomErrorResponse;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> userValidationExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, Object> mperror=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                mperror.put(error.getField(), error.getDefaultMessage()));
         return mperror;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDuplicateEntry(DataIntegrityViolationException ex) {
        String message = "Username already exists. Please use a different email.";
        return new ResponseEntity<>(message, HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> adminIdNotFoundHandler(AdminNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundHandler(UserNotFoundException ex){
        Map<String, Object> mp=new HashMap<>();
        mp.put("TimeStamp" , LocalDate.now());
        mp.put("error" , ex.getMessage());
        return new ResponseEntity<>(mp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<CustomErrorResponse> handleJsonError(JsonProcessingException ex) {
        return ResponseEntity
                .badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid JSON", ex.getOriginalMessage()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingPart(MissingServletRequestPartException ex) {
        return ResponseEntity
                .badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "Missing request part", ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingParam(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "Missing request parameter", ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<CustomErrorResponse> handleIO(IOException ex) {
        return ResponseEntity
                .badRequest()
                .body(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),  "Error while uploading file or reading input", "IO Error"));
    }

    @ExceptionHandler(LodgingServiceException.class)
    public ResponseEntity<CustomErrorResponse> handleLodgingServiceError(LodgingServiceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Service Error", ex.getMessage()));
    }

    @ExceptionHandler(CloudinaryUploadException.class)
    public ResponseEntity<CustomErrorResponse> handleCloudinaryUpload(CloudinaryUploadException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cloudinary Upload Failed", ex.getMessage()));
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidFile(InvalidFileException ex) {
        return ResponseEntity.badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid File", ex.getMessage()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        "Access Denied",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(LodgeNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleLodgeNotFound(LodgeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Lodge Not Found",
                        ex.getMessage()));
    }
    @ExceptionHandler(TourAlreadyExistsWithName.class)
    public ResponseEntity<CustomErrorResponse> tourAlreadyExists(TourAlreadyExistsWithName ex){
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                .body(new CustomErrorResponse(
                        HttpStatus.ALREADY_REPORTED.value(),
                        "Vacation name must be unique",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "No handler found for the requested endpoint",
                        ex.getMessage()));
    }



}
