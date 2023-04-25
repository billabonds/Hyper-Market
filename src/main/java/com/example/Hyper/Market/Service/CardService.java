package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.CardRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CardResponseDto;
import com.example.Hyper.Market.Exception.InvalidCustomerException;
import com.example.Hyper.Market.Exception.MobileNoAlreadyPresentException;
import com.example.Hyper.Market.Model.Card;
import com.example.Hyper.Market.Model.Customer;
import com.example.Hyper.Market.Repository.CardRepository;
import com.example.Hyper.Market.Repository.CustomerRepository;
import com.example.Hyper.Market.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());

        if(customer == null)
            throw new InvalidCustomerException("Customer doesn't Exist in the database ");

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        // create response Dto

        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();

    }
}
