package com.titans.travelbooking.advice;

import com.titans.travelbooking.exception.AdminNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

}
