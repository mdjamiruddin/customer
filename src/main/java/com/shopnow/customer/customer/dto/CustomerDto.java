package com.shopnow.customer.customer.dto;

import com.shopnow.customer.common.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @UniqueEmail(message = "Email must be unique")
    private String email;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Pincode is mandatory")
    private String pincode;
}
