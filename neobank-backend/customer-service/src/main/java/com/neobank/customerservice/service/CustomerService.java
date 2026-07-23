package com.neobank.customerservice.service;

import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.exception.CustomerNotFoundException;
import com.neobank.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        Customer customer1 = new Customer();

        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setDateOfBirth(customer.getDateOfBirth());

        return customerRepository.save(customer1);
    }

    public  List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByID(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));
    }

    public Customer updateCustomerById(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setDateOfBirth(customer.getDateOfBirth());

        return customerRepository.save(existingCustomer);

    }

    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with CustomerId :" + id));

        customerRepository.delete(customer);
    }
}
