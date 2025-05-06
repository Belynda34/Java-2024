package com.security.javasec.transactions;


import com.security.javasec.models.Transaction;
import com.security.javasec.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    public ApiResponse<Transaction> createTransaction(@RequestBody @Valid TransactionDTO dto) {
        return this.transactionService.doTransaction(dto);
    }
}
