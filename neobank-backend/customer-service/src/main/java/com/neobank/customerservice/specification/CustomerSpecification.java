package com.neobank.customerservice.specification;

import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import org.springframework.data.jpa.domain.Specification;


public class CustomerSpecification {
    public static Specification<Customer> hasFirstName(String firstName){
        return ((root, query, criteriaBuilder) ->

                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + firstName.toLowerCase() + "%"
                ));
    }

    public static Specification<Customer> hasLastName(String lastName) {
        return ((root, query, criteriaBuilder) ->

                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%"
                ));
    }

    public static Specification<Customer> hasEmail(String email) {

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"
                ));
    }


    public static Specification<Customer> hasStatus(CustomerStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"),status);
    }
}
