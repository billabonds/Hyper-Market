package com.example.Hyper.Market.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {

    int customerId;

    String cardNo;

    int cvv;
}
