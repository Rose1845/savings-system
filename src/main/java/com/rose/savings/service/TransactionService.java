package com.rose.savings.service;

import com.rose.savings.mappers.TransactionMapper;
import com.rose.savings.model.dto.TransactionDto;
import com.rose.savings.model.entity.Customer;
import com.rose.savings.model.entity.Transaction;
import com.rose.savings.repository.CustomerRepository;
import com.rose.savings.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    /**
     * logic to create transactions for a specific customer
     * **/
    public TransactionDto createTransaction(TransactionDto transactionDto){
        Optional<Customer> customer = customerRepository.findById(transactionDto.getCustomerId());
        Transaction transaction = TransactionMapper.MAPPER.toEntity(transactionDto);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());
        customer.ifPresent(transaction::setCustomer);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.MAPPER.toDto(savedTransaction);

    }
    /**
     * logic to retrieve all transactions
     * **/
    public   List<Transaction> getAllTransactions(){
        return new ArrayList<>(transactionRepository.findAll());
    }
    /**
     * logic to retrieve a transactions by id
     * **/
    public Optional<Transaction> getTransactionById(Long id){
        return transactionRepository.findById(id);
    }
    /**
     * logic to retrieve all transactions for a specific customer
     * **/
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByCustomer_CustomerId(customerId);
    }


    /**
     * logic to calculate the total savings amount for a specific customer
     * **/
    public double calculateTotalSavingsAmountByCustomerId(Long customerId) {
        List<Transaction> transactions = getAllTransactionsByCustomerId(customerId);
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }
    /**
     *  logic to calculate the total savings amount across all users
     * **/

    public double calculateTotalSavingsAmountAcrossAllUsers() {
        List<Transaction> transactions = getAllTransactions();
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

}
