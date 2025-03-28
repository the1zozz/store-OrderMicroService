package com.store.orderService.service.impl;

import com.store.orderService.entities.Order;
import com.store.orderService.external.clients.PaymentService;
import com.store.orderService.external.clients.ProductService;
import com.store.orderService.external.request.PaymentRequest;
import com.store.orderService.model.OrderRequest;
import com.store.orderService.repository.OrderRepository;
import com.store.orderService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository ;
    @Autowired
    private ProductService productService ;
    @Autowired
    private PaymentService paymentService ;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("placing order Request: {} " , orderRequest);

        productService.reduceQuantity(orderRequest.getProductId() , orderRequest.getQuantity()) ;

        log.info("CREATING ORDER IN STATUS CREATED");

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order) ;

        log.info("calling payment service to make payment");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null ;
        try{
            paymentService.doPayment(paymentRequest) ;
            log.info("payment done successfully , changing order status to PLACED");
            orderStatus = "PLACED" ;
        }catch (Exception e){
            log.error("error occured in payment , changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED" ;
        }

        order.setOrderStatus(orderStatus) ;
        orderRepository.save(order) ;

        log.info("Order placed successfully with order Id: {}" , order.getId());
        return order.getId();
    }
}
