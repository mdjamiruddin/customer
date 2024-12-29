package com.shopnow.customer.repository;

import com.shopnow.customer.dto.CustomerDto;
import com.shopnow.customer.entity.Customer;

public class CustomerMapper {
    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPincode(customerDto.getPincode());

        return customer;
    }

    public static CustomerDto toDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPincode(customer.getPincode());

        return customerDto;
    }
}
