package com.example.Hyper.Market.transformer;

import com.example.Hyper.Market.DTO.RequestDto.CardRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CardResponseDto;
import com.example.Hyper.Market.Model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardtype(cardRequestDto.getCardtype())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }

}
