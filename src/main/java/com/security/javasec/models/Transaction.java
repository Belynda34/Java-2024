package com.security.javasec.models;

import com.security.javasec.enums.TT;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(insertable=false, updatable=false)
    private  String customerId;
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
    private String account;
    private double amount=0.0;

    @Enumerated(EnumType.STRING)
    private TT TransactionType;

    private Date BankingDateTime;

    public Transaction(String customerId, Customer customer, String account, double amount, TT transactionType, Date bankingDateTime) {
        this.customerId = customerId;
        this.customer = customer;
        this.account = account;
        this.amount = amount;
        TransactionType = transactionType;
        BankingDateTime = bankingDateTime;
    }
}
