package com.security.javasec.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "customers" )
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String mobile;
    @NotNull
    private Date dob;
    @NotNull
    private String account;
    private double balance =0.0;
    @NotNull
    private Date lastUpdateDateTime;

    public Customer(String firstName, String lastName, String email, String mobile, Date dob, String account, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.account = account;
        this.balance = balance;
    }

    @OneToMany(mappedBy = "customer")
    private List<Transaction> Transactions;


}
