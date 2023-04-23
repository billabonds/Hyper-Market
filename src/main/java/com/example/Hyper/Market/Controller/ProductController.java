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
    @GetMapping("/get/{category}")                                                         // 2nd API - Working...
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable ("category") ProductCategory category){

        return productService.getAllProductsByCategory(category);
    }

}
