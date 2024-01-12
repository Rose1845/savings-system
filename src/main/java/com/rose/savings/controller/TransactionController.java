package com.rose.savings.controller;

import com.rose.savings.advice.SavingsException;
import com.rose.savings.model.dto.TransactionDto;
import com.rose.savings.model.entity.Transaction;
import com.rose.savings.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody @Valid TransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto);
    }
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("{id}/transactions")
    public Optional<Transaction> getTransactionByCustomerId(@PathVariable("id") @Valid Long id) throws SavingsException {
        return transactionService.getTransactionById(id);
    }
    @GetMapping("/savings-amount/{customerId}")
    public double getSavingsAmountForCustomer(@PathVariable Long customerId) throws SavingsException {
        return transactionService.calculateTotalSavingsAmountByCustomerId(customerId);
    }

    @GetMapping("/total-savings")
    public double getTotalSavingsAmountAcrossAllUsers() {
        return transactionService.calculateTotalSavingsAmountAcrossAllUsers();
    }
}
