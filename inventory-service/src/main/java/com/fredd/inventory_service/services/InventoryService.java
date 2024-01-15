package com.fredd.inventory_service.services;

import com.fredd.inventory_service.model.dtos.BaseResponse;
import com.fredd.inventory_service.model.dtos.OrderItemRequest;

import java.util.List;

public interface InventoryService {

    boolean isInStock(String sku);

    BaseResponse areInStock(List<OrderItemRequest> orderItems);
}
