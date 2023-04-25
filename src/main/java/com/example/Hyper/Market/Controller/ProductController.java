package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.ProductRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.ProductResponseDto;
import com.example.Hyper.Market.Enum.ProductCategory;
import com.example.Hyper.Market.Exception.InvalidSellerException;
import com.example.Hyper.Market.Model.Product;
import com.example.Hyper.Market.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")                                                                    // 1st API - done
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {

        ProductResponseDto productResponseDto =  productService.addProduct(productRequestDto);

        return productResponseDto;
    }


    // Get all products of a particular Category
    @GetMapping("/get/{category}")                                                         // 2nd API - done
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable ("category") ProductCategory category){

        return productService.getAllProductsByCategory(category);
    }


    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(
                                                @PathVariable("price") int price,
                                                @PathVariable("category") ProductCategory productCategory){

        return productService.getAllProductsByPriceAndCategory(price,productCategory);
    }






    // ------------------------------  Work (21-04-23) ---------------------------------------------

            //  1. Get all products by seller email-Id.
            //  2. Delete a product by seller-Id & product-Id.
            //  3. Return top 5 Cheapest products.
            //  4. Return all out of stocks products.
            //  5. Return all available products.
            //  6. Return all products that have quantity less than 10.
            //  7. Return the cheapest product in a particular category.

        // -----------------------------------------------------------------------------------------------
}
