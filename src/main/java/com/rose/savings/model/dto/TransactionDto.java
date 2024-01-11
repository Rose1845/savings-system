package com.rose.savings.model.dto;

import com.rose.savings.model.entity.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Date date;
    private String paymentMethod;
    private double amount;
    private Long customerId;
}
