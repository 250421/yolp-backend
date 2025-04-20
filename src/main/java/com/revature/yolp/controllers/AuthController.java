package com.revature.yolp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.dtos.responses.UserSessionDTO;
import com.revature.yolp.models.User;
import com.revature.yolp.services.AuthService;
import com.revature.yolp.services.SessionService;
import com.revature.yolp.utils.custom_exceptions.InvalidSessionException;
import com.revature.yolp.utils.custom_exceptions.SignUpException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {
    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/sign-up")
    public ResponseEntity<NewUserRequest> register(@RequestBody NewUserRequest newUserRequest) {
        if (!authService.signUp(newUserRequest)) {
            throw new SignUpException("Failed to sign up");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSessionDTO> login(@RequestBody NewUserRequest newUserRequest) {
        User user = authService.signIn(newUserRequest);
        UserSessionDTO sessionDTO = sessionService.createSession(user);
        return ResponseEntity.ok(sessionDTO);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> logout() {
        sessionService.invalidateSession();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserSessionDTO> getSession() {
        UserSessionDTO sessionDTO = sessionService.getCurrentSession();
        if (sessionDTO == null) {
            throw new InvalidSessionException("No active session found");
        }
        return ResponseEntity.ok(sessionDTO);
    }
}
