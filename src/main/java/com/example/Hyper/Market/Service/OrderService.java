package com.example.Hyper.Market.Service;

import com.example.Hyper.Market.DTO.RequestDto.OrderRequestDto;
import com.example.Hyper.Market.DTO.ResponseDto.ItemResponseDto;
import com.example.Hyper.Market.DTO.ResponseDto.OrderResponseDto;
import com.example.Hyper.Market.Enum.ProductStatus;
import com.example.Hyper.Market.Exception.InvalidCartException;
import com.example.Hyper.Market.Exception.InvalidCustomerException;
import com.example.Hyper.Market.Exception.InvalidProductException;
import com.example.Hyper.Market.Model.*;
import com.example.Hyper.Market.Repository.CardRepository;
import com.example.Hyper.Market.Repository.CustomerRepository;
import com.example.Hyper.Market.Repository.OrderedRepository;
import com.example.Hyper.Market.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderedRepository;

//    @Autowired
//    private JavaMailSender emailSender;

    // ---------------------------------------------------------------------------------

                                                                                                // 1st API
    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item : cart.getItems()){

            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product out of Stock");
            }
        }

        order.setItems(orderedItems);
        for(Item item : orderedItems){
            item.setOrder(order);
        }

        order.setTotalValue(cart.getCartTotal());

        return order;
    }

                                                                                                // 2nd API
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        // validate customer
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is Invalid !!");
        }

        // validate product
        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new InvalidProductException("Product does not Exist");
        }

        // validate quantity
//        if(orderRequestDto.getRequiredQuantity() > product.getQuantity() ||
//                product.getProductStatus() !=  ProductStatus.AVAILABLE){
//
//            throw new Exception("Product out of Stock");
//        }

        // validate card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());

        if(card == null || card.getCvv() != orderRequestDto.getCvv() || card.getCustomer() != customer){
            throw new InvalidCartException("Your card is not valid");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();

        try {
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskCardNo);
        order.setCustomer(customer);

        order.setTotalValue(item.getRequiredQuantity() * product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order);         // save both order and item

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponseDto> items= new ArrayList<>();
        for(Item itemEntity : savedOrder.getItems()){

            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity() * itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);

        // ----------------------- : Add email integration code : -------------------------------

//        String text = "Congrats !  " + customer.getName() + " Your is placed with amount : " + savedOrder.getTotalValue();
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("libraryprojectapril@gmail.com");
//        message.setTo(card.getCustomer().getMobNo());
//        message.setSubject("Order Placed at Hyper Market");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDto;
    }

                                                                                                // 3rd API
    public String generateMaskedCard(String cardNo){

        String maskCardNo = "";

        for(int i=0;i<cardNo.length()-4;i++){
            maskCardNo += 'X';
        }

        maskCardNo += cardNo.substring(cardNo.length()-4);
        return maskCardNo;
    }

}
