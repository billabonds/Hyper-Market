package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.CheckoutCartRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.CartResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.ItemResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.OrderResponseDto;
import com.example.Hyper.Market.Exception.InvalidCartException;
import com.example.Hyper.Market.Exception.InvalidCustomerException;
import com.example.Hyper.Market.Model.*;
import com.example.Hyper.Market.Repository.CardRepository;
import com.example.Hyper.Market.Repository.CartRepository;
import com.example.Hyper.Market.Repository.CustomerRepository;
import com.example.Hyper.Market.Repository.OrderedRepository;
import com.example.Hyper.Market.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    ProductService productService;

//    @Autowired
//    private JavaMailSender emailSender;

    // ---------------------------------------------------------

                                                                                                // 1st API
    public CartResponseDto saveCart(Integer customerId, Item item){

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + (item.getRequiredQuantity() * item.getProduct().getPrice());
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);

        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);                                          // add
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for(Item st : savedCart.getItems()){

             ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(st);
             itemResponseDtoList.add(itemResponseDto);
            }

            cartResponseDto.setItems(itemResponseDtoList);
            return cartResponseDto;
        }


                                                                                                // 2nd API
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        // customer is valid or not

        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        // card is valid or not
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());

        if(card == null || card.getCvv() != checkoutCartRequestDto.getCvv() || card.getCustomer() != customer){
            throw new InvalidCartException("Your card is not valid");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0){
            throw new Exception("Cart is Empty !!");
        }

        try {
            Ordered order = orderService.placeOrder(customer, card); // throw exception if product goes out of stock.
            customer.getOrderList().add(order);

            resetCart(cart);                                          // add
            Ordered savedOrder = orderedRepository.save(order);


            //prepare orderResponseDto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items= new ArrayList<>();
            for(Item item : savedOrder.getItems()){

                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }

            orderResponseDto.setItems(items);

            //  ----------------------- : Add email integration code : -------------------------------

//            String text = "Congrats !  " + customer.getName() + " Your is placed with amount : " + order.getTotalValue();

//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("libraryprojectapril@gmail.com");
//            message.setTo(cart.getCustomer().getMobNo());
//            message.setSubject("Order Placed");
//            message.setText(text);
//            emailSender.send(message);

            return orderResponseDto;
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);

        for(Item item : cart.getItems()){
            item.setCart(null);
        }

        cart.setNumberOfItems(0);
        cart.getItems().clear();
    }


}

