package com.example.Hyper.Market.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {

    int customerId;

    int productId;

    int requiredQuantity;
}
