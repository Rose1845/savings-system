package com.rose.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rose.savings.SavingsApplication;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SavingsApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUpMethod(){
        customerRepository.deleteAll();
    }

    @Test
    void createCustomer() throws Exception {
        Customer customer =new Customer();
        customer.setName("rose");
        customer.setIdNumber("123455");
        customer.setPhoneNumber("075677889");
        customer.setEmail("tst@gmail.com");
        customer.setMemberNumber("12345");

        ResultActions resultActions = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.email",is(customer.getEmail())))
                .andExpect(jsonPath("$.phoneNumber",is(customer.getPhoneNumber())))
                .andExpect(jsonPath("$.idNumber",is(customer.getIdNumber())))
                .andExpect(jsonPath("$.memberNumber",is(customer.getMemberNumber())));
    }

    @Test
    void getAllCustomers() throws Exception {
        List<Customer> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Customer.builder().name("ros").email("ros@gmail.com").idNumber("123456").memberNumber("23456").phoneNumber("3456778").build());
        listOfEmployees.add(Customer.builder().name("ros1").email("ros1@gmail.com").idNumber("123457").memberNumber("23456").phoneNumber("3456778").build());
        customerRepository.saveAll(listOfEmployees);
        ResultActions response = mockMvc.perform(get("/api/customers"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfEmployees.size())));
    }
}