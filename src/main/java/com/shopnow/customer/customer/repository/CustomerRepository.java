package com.shopnow.customer.customer.repository;

import com.shopnow.customer.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmailAndIdNot(String email, Long id);
}
