package com.example.Hyper.Market.transformer;

import com.example.Hyper.Market.DTO.RequestDto.ProductRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.ProductResponseDto;
import com.example.Hyper.Market.Enum.ProductStatus;
import com.example.Hyper.Market.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())                   // Seller name
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
