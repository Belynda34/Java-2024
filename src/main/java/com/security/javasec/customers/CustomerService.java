package com.security.javasec.customers;

import com.security.javasec.models.Customer;
import com.security.javasec.repositories.CustomerRepository;
import com.security.javasec.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
//
    private final CustomerRepository customerRepository;

    public ApiResponse<Customer> createCustomer(CustomerCreateDTO ccd){
        if(customerRepository.findByEmail(ccd.getEmail()).isPresent())
            return new ApiResponse<>(false,"Email taken",null);
       if(customerRepository.findByAccount(ccd.getAccount()).isPresent())
            return  new ApiResponse<>(false,"Account already taken",null);
        if(customerRepository.findByMobile(ccd.getMobile()).isPresent())
            return new ApiResponse<>(false,"Mobile number alreay taken",null);

        Customer customer = new Customer(
                ccd.getFirstName(),
                ccd.getLastName(),
                ccd.getEmail(),
                ccd.getMobile(),
                ccd.getDob(),
                ccd.getAccount(),
                ccd.getBalance());
        customerRepository.save(customer);
        return  new ApiResponse<>(true,"Customer created successfully",customer);
    }


    public ApiResponse<Customer> updateCustomer(String id,CustomerUpdateDTO cup) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(cup.getFirstName());
        customer.setLastName(cup.getLastName());
        customer.setMobile(cup.getMobile());
        customer.setDob(cup.getDob());
        customer.setAccount(cup.getAccount());

        customerRepository.save(customer);
        return new ApiResponse<>(true,"Updated successfully",customer);
    }

    public ApiResponse<Customer> deleteCustomer(String id){
        customerRepository.deleteById(id);
        return new ApiResponse<>(true,"Customer deleted",null)
;    }

    public ApiResponse<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return new ApiResponse<>(true,"Customers fetched",customers);
    }

    public  ApiResponse<Customer> getCustomer(String id) throws  Exception{
        Customer customer=customerRepository.findById(id).orElseThrow();
        return new ApiResponse<>(true,"Customer fetched",customer);
    }

}
