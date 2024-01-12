package com.rose.savings.service;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.model.entity.Transaction;
import com.rose.savings.repository.CustomerRepository;
import com.rose.savings.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionService transactionService;
    private Transaction transaction;
    @BeforeEach
    void setUpMethod(){
        transactionService = new TransactionService(transactionRepository, customerRepository);
        Customer customer = Customer.builder()
                .customerId(1L)
                .phoneNumber("123456789")
                .memberNumber("1234")
                .name("rose")
                .email("rose@gmail.com")
                .build();
        customerRepository.save(customer);
        transaction = Transaction.builder()
                .id(1L)
                .paymentMethod("MPESA")
                .amount(1000)
                .date(LocalDateTime.now())
                .customer(Customer.builder()
                        .customerId(1L)
                        .phoneNumber("123456789")
                        .memberNumber("1234")
                        .name("rose")
                        .email("rose@gmail.com")
                        .build())
                .build();

    }

    @Test
    void getTransactionById() throws SavingsException {
        given(transactionRepository.findById(1L)).willReturn(Optional.ofNullable(transaction));

        // when
        Transaction savedTransaction = transactionService.getTransactionById(transaction.getId()).get();

        // then
        assertThat(savedTransaction).isNotNull();
    }
}