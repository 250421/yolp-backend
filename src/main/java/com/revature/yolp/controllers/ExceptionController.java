package com.revature.yolp.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.yolp.utils.custom_exceptions.DuplicateRestaurantNameException;
import com.revature.yolp.utils.custom_exceptions.DuplicateUsernameException;
import com.revature.yolp.utils.custom_exceptions.InvalidPasswordException;
import com.revature.yolp.utils.custom_exceptions.InvalidPermissionException;
import com.revature.yolp.utils.custom_exceptions.InvalidSessionException;
import com.revature.yolp.utils.custom_exceptions.InvalidUsernameException;
import com.revature.yolp.utils.custom_exceptions.RestaurantNotFoundException;
import com.revature.yolp.utils.custom_exceptions.SignUpException;
import com.revature.yolp.utils.custom_exceptions.UnauthorizedException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidUsernameException(InvalidUsernameException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPasswordException(InvalidPasswordException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateUsernameException(DuplicateUsernameException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<Map<String, Object>> handleSignUpException(SignUpException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(InvalidSessionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidSessionException(InvalidSessionException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(InvalidPermissionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPermissionException(InvalidPermissionException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(DuplicateRestaurantNameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateRestaurantNameException(
            DuplicateRestaurantNameException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRestaurantNotFoundException(RestaurantNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(404).body(response);
    }
}
