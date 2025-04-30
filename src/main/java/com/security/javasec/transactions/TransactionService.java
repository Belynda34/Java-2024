package com.security.javasec.transactions;

import com.security.javasec.enums.TT;
import com.security.javasec.models.Customer;
import com.security.javasec.models.Transaction;
import com.security.javasec.repositories.CustomerRepository;
import com.security.javasec.repositories.TransactionRepository;
import com.security.javasec.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public ApiResponse<Transaction> doTransaction(TransactionDTO dto) {
        try {
            Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow();
            if (dto.getTransactionType() == TT.SAVING) {
                customer.setBalance(customer.getBalance() + dto.getAmount());
                customerRepository.save(customer);
            } else if (dto.getTransactionType() == TT.WITHDRAW) {
                if(customer.getBalance() <= 0 || customer.getBalance() < dto.getAmount())
                    return  new ApiResponse<>(false,"Don't have enough money to withdraw",null);
                customer.setBalance(customer.getBalance() - dto.getAmount());
                customerRepository.save(customer);
            }else{
                Customer toCustomer = customerRepository.findByAccount(dto.getToAccount()).orElseThrow();
                if(customer.getBalance() <= 0 || customer.getBalance() < dto.getAmount())
                    return new ApiResponse<>(false,"Don't have enough money to transfer",null);
                toCustomer.setBalance(toCustomer.getBalance() + dto.getAmount());
                customer.setBalance(customer.getBalance() - dto.getAmount());
            }
        } catch (Exception e) {

        }
    }
}
