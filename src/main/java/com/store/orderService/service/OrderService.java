package com.store.orderService.service;

import com.store.orderService.model.OrderRequest;
import com.store.orderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
