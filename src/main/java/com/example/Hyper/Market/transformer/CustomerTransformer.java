package com.example.Hyper.Market.transformer;

import com.example.Hyper.Market.DTO.RequestDto.CustomerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CustomerResponseDto;
import com.example.Hyper.Market.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){


        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .address(customerRequestDto.getAddress())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome " + customer.getName() + " to Hyper Market !!!")
                .build();
    }
}
