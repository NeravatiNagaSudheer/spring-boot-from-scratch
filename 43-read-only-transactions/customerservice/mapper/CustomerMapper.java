package com.neobank.customerservice.mapper;

import com.neobank.customerservice.dto.CustomerRequestDto;
import com.neobank.customerservice.dto.CustomerResponseDto;
import com.neobank.customerservice.entity.Customer;
import com.neobank.customerservice.mapper.util.StringMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = StringMapper.class
)
public interface CustomerMapper {

    @Mapping(target = "customerId",ignore = true)
    @Mapping(target = "status",constant = "ACTIVE")
    Customer toEntity(CustomerRequestDto dto);

    @Mapping(target = "firstName",source = "firstName",qualifiedByName = "capitalize")
    @Mapping(target = "lastName",source = "lastName",qualifiedByName = "capitalize")
    @Mapping(target = "fullName",expression = "java(customer.getFirstName() + \" \" + customer.getLastName())")
    CustomerResponseDto toResponseDto(Customer customer);

    List<CustomerResponseDto> toResponseDtoList(List<Customer> customers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customerId",ignore = true)
    @Mapping(target = "status",ignore = true)
    void updateCustomerFromDto(CustomerRequestDto dto, @MappingTarget Customer customer);
}
