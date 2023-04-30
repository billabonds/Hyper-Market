package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.ProductRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.ItemResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.ProductResponseDto;
import com.example.Hyper.Market.Enum.ProductCategory;
import com.example.Hyper.Market.Enum.ProductStatus;
import com.example.Hyper.Market.Exception.InvalidSellerException;
import com.example.Hyper.Market.Model.Item;
import com.example.Hyper.Market.Model.Ordered;
import com.example.Hyper.Market.Model.Product;
import com.example.Hyper.Market.Model.Seller;
import com.example.Hyper.Market.Repository.ProductRepository;
import com.example.Hyper.Market.Repository.SellerRepository;
import com.example.Hyper.Market.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    // -----------------------------------------------------------------------

                                                                                            // 1st API
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller Id does not exist in the Database");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // Add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);                      // save both seller & product

        // create responseDto
        ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
        return productResponseDto;
    }

                                                                                            // 2nd API
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {

        List<Product> products = productRepository.findByProductCategory(category);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
                productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }


                                                                                            // 3rd API
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price,ProductCategory category){

        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){

            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

                                                                                            // 4th API
    public void decreaseProductQuantity(Item item) throws Exception {

            Product product = item.getProduct();
            int quantity = item.getRequiredQuantity();
            int currentQuantity = product.getQuantity();

            if(quantity > currentQuantity){
                throw new Exception("Out of Stock");
            }

            product.setQuantity(currentQuantity - quantity);

            if(product.getQuantity() == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

    }

}
