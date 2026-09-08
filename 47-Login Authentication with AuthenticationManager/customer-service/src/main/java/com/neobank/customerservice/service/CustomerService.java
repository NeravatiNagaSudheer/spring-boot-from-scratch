package com.neobank.customerservice.service;

import com.neobank.customerservice.dto.CustomerPageResponse;
import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.dto.CustomerSearchRequest;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import com.neobank.customerservice.exception.CustomerNotFoundException;
import com.neobank.customerservice.mapper.CustomerMapper;
import com.neobank.customerservice.repository.CustomerRepository;
import com.neobank.customerservice.specification.CustomerSpecification;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

          Customer customer = customerMapper.toEntity(customerRequestDto);

          Customer savedCustomer = customerRepository.save(customer);
          return customerMapper.toResponseDto(savedCustomer);
    }
    @Transactional(readOnly = true)
    public  List<CustomerResponseDto> getAllCustomers() {
        return customerMapper.toResponseDtoList(customerRepository.findAll());

    }
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerByID(Long id) {
        Customer customer =  customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        return customerMapper.toResponseDto(customer);

    }
    @Transactional
    public CustomerResponseDto updateCustomerById(Long id, CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        customerMapper.updateCustomerFromDto(customerRequestDto,existingCustomer);

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.toResponseDto(savedCustomer);


    }
    @Transactional
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

    public List<CustomerResponseDto> searchCustomerByFirstNameAndLastName(String firstName, String lastName) {
        List<Customer> customers = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> searchCustomersByFirstNameOrLastName(String firstName, String lastName) {
        List<Customer> customers = customerRepository.findByFirstNameOrLastName(firstName, lastName);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> searchCustomersByDateOfBirthBetween(LocalDate startDate, LocalDate endDate) {
        List<Customer> customers = customerRepository.findByDateOfBirthBetween(startDate, endDate);
        return customerMapper.toResponseDtoList(customers);
    }

    public List<CustomerResponseDto> searchCustomersByFirstNameStartingWithIgnoreCase(String firstName) {
        List<Customer> customers = customerRepository.findByFirstNameStartingWithIgnoreCase(firstName);
        return customerMapper.toResponseDtoList(customers);
    }
    public List<CustomerResponseDto> searchCustomersByEmailEndingWith(
            String email){

        List<Customer> customers =
                customerRepository.findByEmailEndingWithIgnoreCase(email);

        return customerMapper.toResponseDtoList(customers);
    }
    public List<CustomerResponseDto> getCustomersByStatusOrderByFirstNameAsc(
            CustomerStatus status) {

        List<Customer> customers =
                customerRepository.findByStatusOrderByFirstNameAsc(status);

        return customerMapper.toResponseDtoList(customers);
    }
    public List<CustomerResponseDto> getCustomersByStatuses(
            List<CustomerStatus> statuses) {

        List<Customer> customers =
                customerRepository.findByStatusIn(statuses);

        return customerMapper.toResponseDtoList(customers);
    }

    public CustomerPageResponse searchCustomers(CustomerSearchRequest request,int pageNo,
                                                int pageSize,String sortBy,String sortDir){

        if (pageNo < 0){
            throw new IllegalArgumentException("pageNo cannot be negative");
        }
        if (pageSize <= 0 || pageSize > 50){
            throw new IllegalArgumentException("pageSize must be between 1 and 50");

        }

        Specification<Customer> specification =
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.conjunction();

        if (request.getFirstName() != null && !request.getFirstName().isBlank()){
            specification = specification.and(CustomerSpecification.hasFirstName(request.getFirstName()));
        }

        if (request.getLastName() != null && !request.getLastName().isBlank()){
            specification = specification.and(CustomerSpecification.hasLastName(request.getLastName()));
        }

        if(request.getEmail() != null && !request.getEmail().isBlank()){
            specification = specification.and(CustomerSpecification.hasEmail(request.getEmail()));
        }

        if (request.getStatus() != null){
            specification = specification.and(CustomerSpecification.hasStatus(request.getStatus()));
        }

        List<String> allowedSortFields = List.of(
                "customerId","firstName","lastName","email","status"
        );

        if (!allowedSortFields.contains(sortBy)){
            throw new IllegalArgumentException("Invalid sortBy field");
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Customer> page = customerRepository.findAll(specification,pageable);

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
}
