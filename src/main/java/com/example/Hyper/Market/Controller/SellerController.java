package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.SellerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.SellerResponseDto;
import com.example.Hyper.Market.Exception.EmailAlreadyPresentException;
import com.example.Hyper.Market.Service.SellerService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;


    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ------------------------------  Work (21-04-23) ---------------------------------------------

        //        1. Get a seller by email.
        //        2. Get by id.
        //        3. Get all seller.
        //        4. Update seller info based on email-id.
        //        5. Delete a seller based on email.
        //        6. Delete by Id.
        //        7. Get all sellers of a particular age.

    // -----------------------------------------------------------------------------------------------
}
