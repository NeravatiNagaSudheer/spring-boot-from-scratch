package com.neobank.customerservice.dto;

import com.neobank.customerservice.enums.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerSearchRequest {
    private String firstName;
    private String lastName;
    private String email;
    private CustomerStatus status;
}
