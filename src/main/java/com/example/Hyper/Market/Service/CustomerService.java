package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.CustomerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CustomerResponseDto;
import com.example.Hyper.Market.Exception.MobileNoAlreadyPresentException;
import com.example.Hyper.Market.Model.Cart;
import com.example.Hyper.Market.Model.Customer;
import com.example.Hyper.Market.Repository.CardRepository;
import com.example.Hyper.Market.Repository.CustomerRepository;
import com.example.Hyper.Market.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null)
            throw new MobileNoAlreadyPresentException("Customer Already Exist in the database !!! ");

        // Convert DTO --> Customer

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .Cardtotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        Customer savedCustomer = customerRepository.save(customer);              // save Customer and Cart

        // convert customer  --> customerResponseDto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
