package com.neobank.customerservice.service;

import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.exception.CustomerNotFoundException;
import com.neobank.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        customer.setDateOfBirth(customerRequestDto.getDateOfBirth());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponseDto response = new CustomerResponseDto();
        response.setCustomerId(savedCustomer.getCustomerId());
        response.setFirstName(savedCustomer.getFirstName());
        response.setLastName(savedCustomer.getLastName());
        response.setEmail(savedCustomer.getEmail());
        response.setPhoneNumber(savedCustomer.getPhoneNumber());
        response.setDateOfBirth(savedCustomer.getDateOfBirth());

        return response;
    }

    public  List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerResponseDto dto = new CustomerResponseDto();
                    dto.setCustomerId(customer.getCustomerId());
                    dto.setFirstName(customer.getFirstName());
                    dto.setLastName(customer.getLastName());
                    dto.setEmail(customer.getEmail());
                    dto.setPhoneNumber(customer.getPhoneNumber());
                    dto.setDateOfBirth(customer.getDateOfBirth());
                    return dto;
                })
                .toList();

    }

    public CustomerResponseDto getCustomerByID(Long id) {
        Customer customer =  customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setDateOfBirth(customer.getDateOfBirth());
        return dto;
    }

    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        existingCustomer.setFirstName(customerRequestDto.getFirstName());
        existingCustomer.setLastName(customerRequestDto.getLastName());
        existingCustomer.setEmail(customerRequestDto.getEmail());
        existingCustomer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        existingCustomer.setDateOfBirth(customerRequestDto.getDateOfBirth());

        Customer customer1 = customerRepository.save(existingCustomer);

        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerId(customer1.getCustomerId());
        dto.setFirstName(customer1.getFirstName());
        dto.setLastName(customer1.getLastName());
        dto.setEmail(customer1.getEmail());
        dto.setPhoneNumber(customer1.getPhoneNumber());
        dto.setDateOfBirth(customer1.getDateOfBirth());

        return dto;

    }

    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        customerRepository.delete(customer);
    }


}
