package com.neobank.customerservice.dto;

import com.neobank.customerservice.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private CustomerStatus status;
}
