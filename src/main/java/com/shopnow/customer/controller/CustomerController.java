package com.shopnow.customer.controller;

import com.shopnow.customer.build.Computer;
import com.shopnow.customer.entity.Customer;
import com.shopnow.customer.repository.impl.CustomerService;
import jakarta.validation.Valid;
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

    @GetMapping(value="/test")
    public String test(){
        return "Welcome to Java";
    }

    @PostMapping(value="/add")
    public ResponseEntity<Customer> add(@Valid @RequestBody Customer customer){
        Customer newCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value="/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value="/update/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer){
        Customer newCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(newCustomer);
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
