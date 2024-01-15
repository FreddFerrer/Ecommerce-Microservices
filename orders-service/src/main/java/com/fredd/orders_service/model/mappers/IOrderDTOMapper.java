package com.fredd.orders_service.model.mappers;

import com.fredd.orders_service.model.dtos.OrderItemRequest;
import com.fredd.orders_service.model.dtos.OrderRequest;
import com.fredd.orders_service.model.dtos.OrderResponse;
import com.fredd.orders_service.model.entities.Order;
import com.fredd.orders_service.model.entities.OrderItems;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderDTOMapper {

    Order toOrder(OrderRequest orderRequest);

    OrderRequest toOrderRequest(Order order);

    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "id", ignore = true)  // Ignorar la propiedad id para evitar ambigüedades
    OrderItems toOrderItem(OrderItemRequest orderItemRequest, @Context Order order);
}
