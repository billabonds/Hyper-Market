package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.CustomerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CustomerResponseDto;
import com.example.Hyper.Market.Exception.MobileNoAlreadyPresentException;
import com.example.Hyper.Market.Model.Customer;
import com.example.Hyper.Market.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")                                                                         // 1st API
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        return customerService.addCustomer(customerRequestDto);
    }







    // ------------------------------  Work (24-04-23) ---------------------------------------------

            //  1. View all customers.
            //  2. Get a customer by email/mobNo.
            //  3. Get all customers whose age > 25.
            //  4. Get all customers who use VISA card.
            //  5. Update a customer info by email.
            //  6. Delete a customer by email/mobNo.

    // -----------------------------------------------------------------------------------------------
}
