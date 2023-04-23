package com.example.Hyper.Market.transformer;

import com.example.Hyper.Market.DTO.RequestDto.SellerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.SellerResponseDto;
import com.example.Hyper.Market.Model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass                                // not make any object of this class
public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .age(sellerRequestDto.getAge())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){

        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
