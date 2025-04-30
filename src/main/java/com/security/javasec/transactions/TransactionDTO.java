package com.security.javasec.transactions;

import com.security.javasec.enums.TT;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @NotNull
    private String customerId;
    @NotNull
    private TT transactionType;
    private String toAccount;
    @NotNull
    private double amount;
}
