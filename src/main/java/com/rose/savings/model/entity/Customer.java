package com.rose.savings.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private String memberNumber;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Transaction> transactions;

}
