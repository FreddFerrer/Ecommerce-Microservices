package com.fredd.orders_service.services.impl;

import com.fredd.orders_service.model.dtos.BaseResponse;
import com.fredd.orders_service.model.dtos.OrderItemRequest;
import com.fredd.orders_service.model.dtos.OrderRequest;
import com.fredd.orders_service.model.dtos.OrderResponse;
import com.fredd.orders_service.model.entities.Order;
import com.fredd.orders_service.model.entities.OrderItems;
import com.fredd.orders_service.model.mappers.IOrderDTOMapper;
import com.fredd.orders_service.model.repositories.IOrderRepository;
import com.fredd.orders_service.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor   //reemplaza autowired en la inyeccion de dependencias
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderDTOMapper orderDTOMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();

        return orders.stream().map(orderDTOMapper::toOrderResponse).toList();
    }

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        //Consulta al servicio de inventario
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        //Si no devuelve error continua con el mapeo.
        if (result != null && !result.hasErrors()) {

        //Mapeo de OrderRequest a Order usando Mapstruct
        Order order = orderDTOMapper.toOrder(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());

        // Mapear cada OrderItemRequest a OrderItem usando MapStruct
        List<OrderItems> orderItems = orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> orderDTOMapper.mapOrderItemRequestToOrderItem(orderItemRequest, order))
                .toList();

        order.setOrderItems(orderItems);

        orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Some of the products are not in stock");
        }
    }

}
