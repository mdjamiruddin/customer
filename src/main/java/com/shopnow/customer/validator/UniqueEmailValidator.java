package com.shopnow.customer.validator;

import com.shopnow.customer.repository.CustomerRepository;
import com.shopnow.customer.validator.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true; // Let other annotations handle null/empty checks
        }
        return !customerRepository.existsByEmail(email);
    }
}
