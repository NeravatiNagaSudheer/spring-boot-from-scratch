package com.neobank.customerservice.controller;

import com.neobank.customerservice.dto.LoginRequestDto;
import com.neobank.customerservice.dto.RegisterRequestDto;
import com.neobank.customerservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto request){
        authService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User Register Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto request){
        authService.login(request);
        return ResponseEntity.ok("Login Successful");

    }

}
