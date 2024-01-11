package com.rose.savings.model.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Date date;
    @NotEmpty
    @NotBlank
    @NotNull
    private String paymentMethod;
    @NotNull
    @Min(100)
    private double amount;
    @NotNull
    private Long customerId;
}
