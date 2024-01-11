package com.rose.savings.service;

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
    public Customer createCustomer(CustomerDto customerDto){
        Customer customer = Customer.builder()
                .email(customerDto.getEmail())
                .memberNumber(customerDto.getMemberNumber())
                .idNumber(customerDto.getIdNumber())
                .phoneNumber(customerDto.getPhoneNumber())
                .name(customerDto.getName())
                .build();
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customerRepository.findAll());

    }
}
