package com.rose.savings.repository;

import com.rose.savings.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
