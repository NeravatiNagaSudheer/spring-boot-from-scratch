package com.neobank.customerservice.controller;

import com.neobank.customerservice.dto.CustomerPageResponse;
import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.service.CustomerService;
import com.neobank.customerservice.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto savedCustomer = customerService.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerByID(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomerById(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto updatedCustomer = customerService.updateCustomerById(id, customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/page")
    public CustomerPageResponse getCustomers(@RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                             @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                             @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                             @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir){
        return customerService.getAllCustomers(
                pageNo,
                pageSize,
                sortBy,
                sortDir
        );

    }

}
