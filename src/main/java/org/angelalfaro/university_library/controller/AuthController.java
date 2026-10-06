package org.angelalfaro.university_library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.angelalfaro.university_library.dto.Auth.AuthResponse;
import org.angelalfaro.university_library.dto.Auth.LoginRequest;
import org.angelalfaro.university_library.dto.Auth.RegisterRequest;
import org.angelalfaro.university_library.service.Auth.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}