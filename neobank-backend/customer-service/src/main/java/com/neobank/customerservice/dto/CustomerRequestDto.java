package com.neobank.customerservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {


    @NotBlank(message = "firstName Should not be null or empty")
    private String firstName;

    @NotBlank(message = "lastName Should not be null or empty")
    private String lastName;


    @NotBlank(message = "Email Should not be blank")
    @Email(message = "Please enter a valid email")
    private String email;


    @NotBlank(message = "Phone Number should not be blank")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone Number must be a valid 10-digit Indian mobile number"
    )
    private String phoneNumber;

    @NotNull(message = "Date of Birth is required")
    @Past(
            message = "Please Enter a valid Date of Birth")
    private LocalDate dateOfBirth;



}
