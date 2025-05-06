package com.security.javasec.customers;


import com.security.javasec.models.Customer;
import com.security.javasec.utils.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/create")
    public ApiResponse<Customer> createCustomer(@RequestBody @Valid CustomerCreateDTO createDTO) {
        return  this.customerService.createCustomer(createDTO);
    }

    @SecurityRequirement(name = "BearerAuth")
    @PutMapping("/update")
    public ApiResponse<Customer> updateCustomer(@RequestBody @Valid CustomerUpdateDTO updateDTO, @RequestParam String id) throws  Exception{
        return this.customerService.updateCustomer(id,updateDTO);
    }



}
