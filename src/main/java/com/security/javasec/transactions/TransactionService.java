package com.security.javasec.transactions;

import com.security.javasec.enums.TT;
import com.security.javasec.models.Customer;
import com.security.javasec.models.Transaction;
import com.security.javasec.repositories.CustomerRepository;
import com.security.javasec.repositories.TransactionRepository;
import com.security.javasec.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    private final JavaMailSender mailSender;

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

            Transaction tx = new Transaction(
                    dto.getCustomerId(),
                    customer,
                    dto.getToAccount(),
                    dto.getAmount(),
                    dto.getTransactionType(),
                    new Date()
            );

            transactionRepository.save(tx);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rumu.belynda@gmail.com");
            message.setTo(customer.getEmail());
            message.setSubject("NATIONAL BANK OF RWANDA SYSTEM");
            message.setText("Dear "+customer.getFirstName()+" ,your "+
                    dto.getTransactionType().name().toLowerCase()+" of "
                    +dto.getAmount()+"on your account with account "+
                    dto.getToAccount()+" has completed successfully \n\n\n from BNR");
            mailSender.send(message);
            return new ApiResponse<>(true,"transaction Performed successfully",tx);
        } catch (Exception e) {
            return  new ApiResponse<>(false,e.getMessage()  == "No value present" ? "Customer doesn't exist" : e.getMessage(),null);
        }
    }
}
