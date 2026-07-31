package com.neobank.customerservice.service;

import com.neobank.customerservice.dto.CustomerPageResponse;
import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import com.neobank.customerservice.exception.CustomerNotFoundException;
import com.neobank.customerservice.mapper.CustomerMapper;
import com.neobank.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public CustomerPageResponse getAllCustomers(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Customer> page = customerRepository.findAll(pageable);
        List<CustomerResponseDto> customers = customerMapper.toResponseDtoList(page.getContent());

        return CustomerPageResponse.builder()
                .customers(customers)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    public List<CustomerResponseDto> getCustomersByFirstName(String firstName){
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        return customerMapper.toResponseDtoList(customers);

    }
    public List<CustomerResponseDto> getCustomersByLastName(String lastName){
        List<Customer> customers = customerRepository.findByLastName(lastName);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> getCustomersByEmail(String email){
        List<Customer> customers = customerRepository.findByEmail(email);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> getCustomersByStatus(CustomerStatus status){
        List<Customer> customers = customerRepository.findByStatus(status);
        return customerMapper.toResponseDtoList(customers);
    }

    //Partial-Search
    public List<CustomerResponseDto> searchCustomersByFirstName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase(firstName);
        return customerMapper.toResponseDtoList(customers);

    }

    public List<CustomerResponseDto> searchCustomersByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastNameContainingIgnoreCase(lastName);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> searchCustomersByEmail(String email) {
        List<Customer> customers = customerRepository.findByEmailContainingIgnoreCase(email);
        return customerMapper.toResponseDtoList(customers);
    }
}
