package com.jospin.order_service.order.mapper;

import org.springframework.stereotype.Component;

import com.jospin.order_service.order.dto.OrderRequestDTO;
import com.jospin.order_service.order.dto.OrderResponseDTO;
import com.jospin.order_service.order.entity.Order;
import com.jospin.order_service.order_item.dto.OrderItemDTO;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDTO dto) {

        return Order.builder()
                .userId(dto.getUserId())
                .build();
    }

    public OrderResponseDTO toResponseDTO(Order order) {

        return OrderResponseDTO.builder()
                .id(order.getId())
                .user(null)
                .status(order.getStatus().name())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .items(order.getItems()
                    .stream()
                    .map(i -> OrderItemDTO.builder()
                            .productName(i.getProductName())
                            .quantity(i.getQuantity())
                            .price(i.getPrice())
                            .build())
                    .toList())
            .build();
    }
}
