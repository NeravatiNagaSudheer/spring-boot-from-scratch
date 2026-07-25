package com.neobank.customerservice.service;

import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.exception.CustomerNotFoundException;
import com.neobank.customerservice.mapper.CustomerMapper;
import com.neobank.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

          Customer customer = customerMapper.toEntity(customerRequestDto);

          Customer savedCustomer = customerRepository.save(customer);
          return customerMapper.toResponseDto(savedCustomer);
    }

    public  List<CustomerResponseDto> getAllCustomers() {
        return customerMapper.toResponseDtoList(customerRepository.findAll());

    }

    public CustomerResponseDto getCustomerByID(Long id) {
        Customer customer =  customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        return customerMapper.toResponseDto(customer);

    }

    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        customerMapper.updateCustomerFromDto(customerRequestDto,existingCustomer);

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.toResponseDto(savedCustomer);


    }

    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        customerRepository.delete(customer);
    }


}
