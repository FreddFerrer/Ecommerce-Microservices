package com.fredd.orders_service.services;

import com.fredd.orders_service.model.dtos.OrderRequest;
import com.fredd.orders_service.model.dtos.OrderResponse;

import java.util.List;

public interface IOrderService {

    List<OrderResponse> getAllOrders();

    void placeOrder(OrderRequest orderRequest);
}
