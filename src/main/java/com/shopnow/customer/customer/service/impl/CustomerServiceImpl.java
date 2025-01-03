package com.shopnow.customer.customer.service.impl;

import com.shopnow.customer.common.exception.DuplicateEmailException;
import com.shopnow.customer.common.util.Mapper;
import com.shopnow.customer.customer.dto.CustomerDto;
import com.shopnow.customer.customer.entity.Customer;
import com.shopnow.customer.customer.repository.CustomerRepository;
import com.shopnow.customer.customer.service.CustomerService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Mapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Mapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = mapper.mapToCustomerEntity(customerDto);
        Customer resCustomer = saveData(customer);
        CustomerDto resCustomerDto = mapper.mapToCustomerDto(resCustomer);

        return resCustomerDto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> mapper.mapToCustomerDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        customerDto.setId(id);
        // Find the existing customer by ID
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            // Update the customer's details
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(customerDto.getAddress());
            customer.setPincode(customerDto.getPincode());
            // Save the updated customer entity
            Customer resCustomer = customerRepository.save(customer);
            // Convert the updated customer entity to a CustomerDto
            return mapper.mapToCustomerDto(resCustomer);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    /**
     * Save a customer
     *
     * @param customer the entity to save
     * @return the persisted entity
     */
    public Customer saveData(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateEmailException("Email is already in use!");
            }
            throw ex;
        }
    }
}
