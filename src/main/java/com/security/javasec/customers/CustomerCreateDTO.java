package com.security.javasec.customers;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomerCreateDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email(message = "Invalid email")
    private String email;
    @NotNull
    @Pattern(regexp = "\\d{10}",message = "Invalid phone number ")
    private String mobile;
    @NotNull
    @Past(message = "Invalid date of birth")
    private Date dob;
    @NotNull
    @Size(min=10,max=10,message = "Invalid account")
    private String account;
    @NotNull
    private Double balance;
}
