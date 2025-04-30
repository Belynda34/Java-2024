package com.security.javasec.models;

import com.security.javasec.enums.TT;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "transaction")
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    private String account;
    private double amount=0.0;

    @Enumerated(EnumType.STRING)
    private TT TransactionType;

    private Date BankingDateTime;



}
