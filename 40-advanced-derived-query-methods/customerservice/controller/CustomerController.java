package com.neobank.customerservice.controller;

import com.neobank.customerservice.dto.CustomerPageResponse;
import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import com.neobank.customerservice.service.CustomerService;
import com.neobank.customerservice.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/search/firstname")
    public List<CustomerResponseDto> getCustomersByFirstName(@RequestParam String firstName){
        return customerService.getCustomersByFirstName(firstName);
    }

    @GetMapping("/search/lastname")
    public List<CustomerResponseDto> getCustomersByLastName(@RequestParam String lastName){
        return customerService.getCustomersByLastName(lastName);
    }

    @GetMapping("/search/email")
    public List<CustomerResponseDto> getCustomersByEmail(@RequestParam String email){
        return customerService.getCustomersByEmail(email);
    }
    @GetMapping("/search/status")
    public List<CustomerResponseDto> getCustomersByStatus(@RequestParam CustomerStatus status){
        return customerService.getCustomersByStatus(status);
    }

    @GetMapping("/search/firstname/contains")
    public List<CustomerResponseDto> searchCustomersByFirstName(@RequestParam String firstName){
        return customerService.searchCustomersByFirstName(firstName);
    }

    @GetMapping("/search/lastname/contains")
    public List<CustomerResponseDto> searchCustomersByLastName(@RequestParam String lastName){
        return customerService.searchCustomersByLastName(lastName);
    }

    @GetMapping("/search/email/contains")
    public List<CustomerResponseDto> searchCustomersByEmail(@RequestParam String email){
        return customerService.searchCustomersByEmail(email);
    }

    @GetMapping("/search")
    public List<CustomerResponseDto> searchByCustomerFirstNameAndLastName(@RequestParam String firstName,@RequestParam String lastName){
        return customerService.searchCustomerByFirstNameAndLastName(firstName,lastName);
    }

    @GetMapping("/search/or")
    public  List<CustomerResponseDto> searchByFirstNameOrLastName(@RequestParam String firstName,@RequestParam String lastName){
        return customerService.searchCustomersByFirstNameOrLastName(firstName,lastName);
    }
    @GetMapping("/search/dob")
    public List<CustomerResponseDto> searchByDateOfBirth(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        return customerService.searchCustomersByDateOfBirthBetween(startDate,endDate);
    }
    @GetMapping("/search/firstname/startsWith")
    public List<CustomerResponseDto> searchCustomersByFirstNameStartingWithIgnoreCase(@RequestParam String firstName){
        return customerService.searchCustomersByFirstNameStartingWithIgnoreCase(firstName);
    }
    @GetMapping("/search/email/endsWith")
    public List<CustomerResponseDto> searchCustomersByEmailEndingWith(
            @RequestParam String email){

        return customerService.searchCustomersByEmailEndingWith(email);
    }
    @GetMapping("/search/status/orderBy/firstName")
    public List<CustomerResponseDto> getCustomersByStatusOrderByFirstNameAsc(
            @RequestParam CustomerStatus status) {

        return customerService.getCustomersByStatusOrderByFirstNameAsc(status);
    }
    @GetMapping("/search/status/in")
    public List<CustomerResponseDto> getCustomersByStatuses(
            @RequestParam List<CustomerStatus> statuses) {

        return customerService.getCustomersByStatuses(statuses);
    }


}
