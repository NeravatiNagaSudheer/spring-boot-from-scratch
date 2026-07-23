package com.neobank.customerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

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
