package com.security.javasec.customers;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Pattern(regexp = "\\d{10}",message = "Invalid phone number ")
    private String mobile;
    @NotNull
    @Past(message = "Invalid date of birth")
    private Date dob;
    @NotNull
    @Size(min=10,max=10,message = "Invalid account")
    private String account;


}
