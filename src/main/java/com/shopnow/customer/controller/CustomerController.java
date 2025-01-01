package com.shopnow.customer.controller;

import com.shopnow.customer.build.Computer;
import com.shopnow.customer.dto.CustomerDto;
import com.shopnow.customer.entity.Customer;
import com.shopnow.customer.repository.impl.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value="/add")
    public ResponseEntity<CustomerDto> add(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto resCustomerDto = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(resCustomerDto);
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value="/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value="/update/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto){
        CustomerDto resCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(resCustomer);
    }

    @GetMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping(value="/computer")
    public ResponseEntity<Computer> getComputer() {
        /*
       Computer computer = new Computer
               .ComputerBuilder("500 GB", "2 GB")
               .setBluetoothEnabled(true)
               .setGraphicsCardEnabled(true)
               .build();
         */

        Computer computer = new Computer
                .ComputerBuilder()
                .setHDD("500 GB")
                .setRAM("2 GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true)
                .build();
        return ResponseEntity.ok(computer);
    }

}
