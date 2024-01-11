package com.rose.savings.controller;

import com.rose.savings.model.dto.TransactionDto;
import com.rose.savings.model.entity.Transaction;
import com.rose.savings.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto);
    }
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("{id}/transactions")
    public Optional<Transaction> getTransactionByCustomerId(@PathVariable("id") Long id){
        return transactionService.getTransactionById(id);
    }
}
