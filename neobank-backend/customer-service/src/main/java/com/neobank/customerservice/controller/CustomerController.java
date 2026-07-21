package com.neobank.customerservice.controller;

import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.service.CustomerService;
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
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerByID(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id,@RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomerById(id,customer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCustomer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
