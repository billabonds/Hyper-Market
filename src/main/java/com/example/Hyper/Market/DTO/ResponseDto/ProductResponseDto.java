package com.example.Hyper.Market.DTO.ResponseDto;

import com.example.Hyper.Market.Enum.ProductCategory;
import com.example.Hyper.Market.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    int quantity;

    ProductStatus productStatus;
}
