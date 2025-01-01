package com.shopnow.customer.repository.impl;

import com.shopnow.customer.dto.CustomerDto;
import com.shopnow.customer.entity.Customer;
import com.shopnow.customer.exception.DuplicateEmailException;
import com.shopnow.customer.repository.CustomerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Save a customer.
     *
     * @param customerDto the entity to save
     * @return the persisted entity
     */
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer resCustomer = saveData(customer);
        CustomerDto resCustomerDto = modelMapper.map(resCustomer, CustomerDto.class);
        return resCustomerDto;
    }

    /**
     * get all customers.
     *
     * @return the persisted entity
     */
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    /**
     * get all customers.
     *
     * @return the persisted entity
     */
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Update a customer
     *
     * @param CustomerDto the entity to save
     * @return the persisted entity
     */
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
            return modelMapper.map(resCustomer, CustomerDto.class);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    /**
     * Delete the product by ID.
     *
     * @param id the ID of the entity
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * Save a customer.
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

    public boolean existsByEmail(String email, Long id){
        return customerRepository.existsByEmailAndIdNot(email, id);
    }
}
