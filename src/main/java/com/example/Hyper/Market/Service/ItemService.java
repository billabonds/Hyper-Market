package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.ItemRequestDto;
import com.example.Hyper.Market.Enum.ProductStatus;
import com.example.Hyper.Market.Exception.InvalidCustomerException;
import com.example.Hyper.Market.Exception.InvalidProductException;
import com.example.Hyper.Market.Model.Customer;
import com.example.Hyper.Market.Model.Item;
import com.example.Hyper.Market.Model.Product;
import com.example.Hyper.Market.Repository.CustomerRepository;
import com.example.Hyper.Market.Repository.ItemRepository;
import com.example.Hyper.Market.Repository.ProductRepository;
import com.example.Hyper.Market.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    // ------------------------------------------------------------------------------------------------
                                                                                                        // 1st API
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        // customer valid or not

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is Invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new InvalidProductException("Product does not Exist");
        }

        if(itemRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() !=  ProductStatus.AVAILABLE){

            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setProduct(product);

        product.getItemList().add(item);

        return itemRepository.save(item);
    }
}
