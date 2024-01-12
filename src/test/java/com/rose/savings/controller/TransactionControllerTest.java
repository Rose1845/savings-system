package com.rose.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rose.savings.mappers.TransactionMapper;
import com.rose.savings.model.dto.TransactionDto;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.model.entity.Transaction;
import com.rose.savings.repository.CustomerRepository;
import com.rose.savings.repository.TransactionRepository;
import com.rose.savings.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUpMethod(){
        transactionRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void createTransactionReturnError() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("rose");
        customer.setIdNumber("123455");
        customer.setPhoneNumber("075677889");
        customer.setEmail("tst@gmail.com");
        customer.setMemberNumber("12345");


        // Create a TransactionDto
        TransactionDto transactionDto = TransactionDto.builder()
                .date(LocalDateTime.now())
                .paymentMethod("Mpesa")
                .amount(100.0)
                .customerId(1L)
                .build();
        ResultActions resultActions = mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        );

        // Assert
        resultActions.andDo(print()).andExpect(status().isBadRequest());

    }
    @Test
    void getAllTransactions() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/transactions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void getTransactionByCustomerIdReturnError() throws Exception {
        long customerId =1L;
        Customer customer =new Customer();
        customer.setName("rose");
        customer.setIdNumber("123455");
        customer.setPhoneNumber("075677889");
        customer.setEmail("tst@gmail.com");
        customer.setMemberNumber("12345");
        customerRepository.save(customer);
        TransactionDto transactionDto = TransactionDto.builder()
                .date(LocalDateTime.now())
                .paymentMethod("Mpesa")
                .amount(100.0)
                .customerId(1L)
                .build();


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/transactions/{id}/transactions", transactionDto.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isBadRequest())
                .andReturn();


    }

    @Test
    void getSavingsAmountForCustomerReturnError() throws Exception {
        long customerId =1L;
        TransactionDto transactionDto = TransactionDto.builder()
                .date(LocalDateTime.now())
                .paymentMethod("Mpesa")
                .amount(100.0)
                .customerId(1L)
                .build();


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/transactions/savings-amount/{customerId}", transactionDto.getCustomerId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto))
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void getTotalSavingsAmountAcrossAllUsers() throws Exception {
        // Perform GET request to retrieve total savings amount across all users
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/transactions/total-savings")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
