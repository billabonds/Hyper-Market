package com.example.Hyper.Market.DTO.RequestDto;

import com.example.Hyper.Market.Enum.ProductCategory;
import com.example.Hyper.Market.Model.Seller;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    int sellerId;

    String productName;

    int price;

    int quantity;

    ProductCategory productCategory;
}
