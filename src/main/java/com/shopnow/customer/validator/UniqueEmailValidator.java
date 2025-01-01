package com.shopnow.customer.validator;

import com.shopnow.customer.annotation.UniqueEmail;
import com.shopnow.customer.dto.CustomerDto;
import com.shopnow.customer.repository.CustomerRepository;
import com.shopnow.customer.repository.impl.CustomerService;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        System.out.println("validator context: "+ context);
        if (email == null || email.isEmpty()) {
            return true; // Allow null or empty emails; other annotations handle this.
        }
        Long id = Long.valueOf(0);
        return !customerService.existsByEmail(email, id);
    }
}
