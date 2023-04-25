package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.CardRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CardResponseDto;
import com.example.Hyper.Market.Exception.InvalidCustomerException;
import com.example.Hyper.Market.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {

        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        }
        catch (InvalidCustomerException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // ------------------------------  Work (24-04-23) ---------------------------------------------

        //  1. Get all VISA card.
        //  2. Get all MASTERCARD cards whose  expiry-date > 1 Jan,2025.
        //  3. Return the CardType which has maximum number of that card.

    // -----------------------------------------------------------------------------------------------
}
