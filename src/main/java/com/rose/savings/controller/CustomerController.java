package com.rose.savings.controller;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.model.dto.CustomerDto;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.repository.CustomerRepository;
import com.rose.savings.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid CustomerDto customerDto) throws SavingsException {
        return customerService.createCustomer(customerDto);
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
