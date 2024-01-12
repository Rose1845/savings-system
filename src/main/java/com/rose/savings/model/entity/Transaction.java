package com.rose.savings.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String paymentMethod;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Transaction(long id, String s, String mpesa, String number, long l) {
    }
}