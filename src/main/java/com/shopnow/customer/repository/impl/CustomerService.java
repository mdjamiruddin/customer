package com.shopnow.customer.repository.impl;

import com.shopnow.customer.entity.Customer;
import com.shopnow.customer.exception.DuplicateEmailException;
import com.shopnow.customer.repository.CustomerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Save a customer.
     *
     * @param customer the entity to save
     * @return the persisted entity
     */
    public Customer saveCustomer(Customer customer) {
        return saveData(customer);
    }

    /**
     * get all customers.
     *
     * @return the persisted entity
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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
     * @param customer the entity to save
     * @return the persisted entity
     */
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer eCustomer = existingCustomer.get();
            eCustomer.setName(customer.getName());
            eCustomer.setEmail(customer.getEmail());
            eCustomer.setAddress(customer.getAddress());
            eCustomer.setPincode(customer.getPincode());
            return saveData(eCustomer);
        } else {
            throw new RuntimeException("Customer not found");
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
}
