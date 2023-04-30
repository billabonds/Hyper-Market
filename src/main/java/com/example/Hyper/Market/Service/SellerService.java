package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.SellerRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.SellerResponseDto;
import com.example.Hyper.Market.Exception.EmailAlreadyPresentException;
import com.example.Hyper.Market.Model.Seller;
import com.example.Hyper.Market.Repository.SellerRepository;
import com.example.Hyper.Market.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

                                                                                                    // 1st API
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setAge(sellerRequestDto.getAge());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null){
            throw new EmailAlreadyPresentException("Email Id Already Exist in the Database ");
        }

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // prepare responseDto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(seller);
        return sellerResponseDto;
    }
}
