package com.shopnow.customer.customer.service;

import com.shopnow.customer.customer.dto.CustomerDto;
import com.shopnow.customer.customer.entity.Customer;
import com.shopnow.customer.common.exception.DuplicateEmailException;
import com.shopnow.customer.customer.repository.CustomerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    Optional<Customer> getCustomerById(Long id);
    void deleteCustomer(Long id);
}
