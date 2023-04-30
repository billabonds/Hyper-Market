package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.CheckoutCartRequestDto;
import com.example.Hyper.Market.DTO.RequestDto.ItemRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CartResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.CustomerResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.OrderResponseDto;
import com.example.Hyper.Market.Model.Item;
import com.example.Hyper.Market.Service.CartService;
import com.example.Hyper.Market.Service.ItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;



    @PostMapping("/add")                                                                             // 1st API
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/checkout")                                                                        // 2nd API
    public OrderResponseDto checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkoutCart(checkoutCartRequestDto);
    }







    // ------------------------------  Work (24-04-23) ---------------------------------------------

            //  1. Remove from cart.
            //  2. View all items in cart.
            //  3. Email sending.

    // -----------------------------------------------------------------------------------------------

}
