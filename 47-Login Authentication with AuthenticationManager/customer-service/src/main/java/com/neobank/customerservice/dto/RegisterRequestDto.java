package com.neobank.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
}
