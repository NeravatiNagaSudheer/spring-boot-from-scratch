package com.neobank.customerservice.entity;

import com.neobank.customerservice.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

}
