package com.example.Hyper.Market.transformer;

import com.example.Hyper.Market.DTO.RequestDto.ItemRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.ItemResponseDto;
import com.example.Hyper.Market.Model.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity() * item.getProduct().getPrice())
                .build();
    }

}
