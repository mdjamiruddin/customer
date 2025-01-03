package com.shopnow.customer.common.util;

import com.shopnow.customer.customer.dto.CustomerDto;
import com.shopnow.customer.customer.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Map Customer Entity to CustomerDTO
    public CustomerDto mapToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    // Map CustomerDTO to Customer Entity
    public Customer mapToCustomerEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
