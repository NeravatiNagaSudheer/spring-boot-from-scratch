package com.neobank.customerservice.mapper;

import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequestDto dto);
    CustomerResponseDto toResponseDto(Customer customer);
    List<CustomerResponseDto> toResponseDtoList(List<Customer> customers);
    void updateCustomerFromDto(CustomerRequestDto dto, @MappingTarget Customer customer);
}
