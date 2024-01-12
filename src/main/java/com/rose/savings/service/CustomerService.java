package com.rose.savings.service;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.mappers.CustomerMapper;
import com.rose.savings.model.dto.CustomerDto;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    /**
     * logic to create a customer
     * **/
    public Customer createCustomer(CustomerDto customerDto) throws SavingsException {
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw  SavingsException.builder().message("Email address already in use").metadata("email exists").build();
        }
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
            throw  SavingsException.builder().message("phone number already in use").metadata("phone number exists").build();
        }
        if (customerRepository.existsByIdNumber(customerDto.getIdNumber())) {
            throw  SavingsException.builder().message("id number already in use").metadata("id number exists").build();
        }
        if (customerRepository.existsByMemberNumber(customerDto.getMemberNumber())) {
            throw  SavingsException.builder().message("member number already in use").metadata("member number exists").build();
        }
        Customer customer = Customer.builder()
                .email(customerDto.getEmail())
                .memberNumber(customerDto.getMemberNumber())
                .idNumber(customerDto.getIdNumber())
                .phoneNumber(customerDto.getPhoneNumber())
                .name(customerDto.getName())
                .build();
        return customerRepository.save(customer);
    }
    /**
     * logic to retrieve all customers
     * **/
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customerRepository.findAll());

    }
}
