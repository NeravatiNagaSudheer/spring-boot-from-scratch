package com.neobank.customerservice.service;

import com.neobank.customerservice.dto.LoginRequestDto;
import com.neobank.customerservice.dto.RegisterRequestDto;
import com.neobank.customerservice.entity.AppUser;
import com.neobank.customerservice.repository.AppUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public void login(LoginRequestDto loginRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        ));
    }

    public void registerUser(@Valid RegisterRequestDto request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        appUserRepository.save(user);
    }
}
