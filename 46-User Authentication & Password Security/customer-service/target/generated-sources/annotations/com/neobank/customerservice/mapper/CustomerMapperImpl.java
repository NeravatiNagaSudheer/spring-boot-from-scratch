package com.neobank.customerservice.mapper;

import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.enums.CustomerStatus;
import com.neobank.customerservice.mapper.util.StringMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-30T17:57:05+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Autowired
    private StringMapper stringMapper;

    @Override
    public Customer toEntity(CustomerRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( dto.getFirstName() );
        customer.setLastName( dto.getLastName() );
        customer.setEmail( dto.getEmail() );
        customer.setPhoneNumber( dto.getPhoneNumber() );
        customer.setDateOfBirth( dto.getDateOfBirth() );

        customer.setStatus( CustomerStatus.ACTIVE );

        return customer;
    }

    @Override
    public CustomerResponseDto toResponseDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();

        customerResponseDto.setFirstName( stringMapper.capitalize( customer.getFirstName() ) );
        customerResponseDto.setLastName( stringMapper.capitalize( customer.getLastName() ) );
        customerResponseDto.setCustomerId( customer.getCustomerId() );
        customerResponseDto.setEmail( customer.getEmail() );
        customerResponseDto.setPhoneNumber( customer.getPhoneNumber() );
        customerResponseDto.setDateOfBirth( customer.getDateOfBirth() );
        customerResponseDto.setStatus( customer.getStatus() );

        customerResponseDto.setFullName( customer.getFirstName() + " " + customer.getLastName() );

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> toResponseDtoList(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerResponseDto> list = new ArrayList<CustomerResponseDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toResponseDto( customer ) );
        }

        return list;
    }

    @Override
    public void updateCustomerFromDto(CustomerRequestDto dto, Customer customer) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFirstName() != null ) {
            customer.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            customer.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            customer.setEmail( dto.getEmail() );
        }
        if ( dto.getPhoneNumber() != null ) {
            customer.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getDateOfBirth() != null ) {
            customer.setDateOfBirth( dto.getDateOfBirth() );
        }
    }
}
