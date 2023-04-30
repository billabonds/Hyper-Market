package com.example.Hyper.Market.DTO.ResponseDto;

import com.example.Hyper.Market.Model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> items;

    String customerName;
}
