package com.neobank.customerservice.repository;

import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

            //Exact-Search
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByEmail(String email);
    List<Customer> findByStatus(CustomerStatus status);

    //Case-Insensitive
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    List<Customer> findByEmailContainingIgnoreCase(String email);
}
