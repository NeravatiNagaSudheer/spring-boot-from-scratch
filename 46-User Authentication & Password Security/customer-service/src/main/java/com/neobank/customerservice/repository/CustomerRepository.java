package com.neobank.customerservice.repository;

import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

            //Exact-Search
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByEmail(String email);
    List<Customer> findByStatus(CustomerStatus status);

    //Case-Insensitive
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    List<Customer> findByEmailContainingIgnoreCase(String email);

    List<Customer> findByFirstNameAndLastName(String firstName,String lastName);
    List<Customer> findByFirstNameOrLastName(String firstName,String lastName);
    List<Customer> findByDateOfBirthBetween(LocalDate startDate,LocalDate endDate);
    List<Customer> findByFirstNameStartingWithIgnoreCase(String firstName);
    List<Customer> findByEmailEndingWithIgnoreCase(String email);
    List<Customer> findByStatusOrderByFirstNameAsc(CustomerStatus status);
    List<Customer> findByStatusIn(List<CustomerStatus> statuses);
}
