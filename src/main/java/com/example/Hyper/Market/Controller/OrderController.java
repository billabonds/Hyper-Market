package com.example.Hyper.Market.Controller;

import com.example.Hyper.Market.DTO.RequestDto.ItemRequestDto;
import com.example.Hyper.Market.DTO.RequestDto.OrderRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.OrderResponseDto;
import com.example.Hyper.Market.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    // API to order and item individually
    @Autowired
    OrderService orderService;


    @PostMapping("/place")                                                                       // 1st API
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {

        return orderService.placeOrder(orderRequestDto);
    }





    // ------------------------------  Work (27-04-23) ---------------------------------------------

        //  1. Get all the orders for a customer.
        //  2. Get recent 5 orders.
        //  3. Delete an order from the order list.
        //  4. Select the order and also tell the customer name with the highest totalValue.

    // -----------------------------------------------------------------------------------------------

}
