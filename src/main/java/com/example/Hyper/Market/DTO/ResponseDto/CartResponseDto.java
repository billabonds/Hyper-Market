package com.example.Hyper.Market.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    Integer cartTotal;

    Integer numberOfItems;

    String customerName;

    List<ItemResponseDto> items;
}
