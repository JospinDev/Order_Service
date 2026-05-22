package com.jospin.order_service.order.service;

import com.jospin.order_service.order.dto.OrderRequestDTO;
import com.jospin.order_service.order.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO dto);

    OrderResponseDTO getOrderById(Long id);

    List<OrderResponseDTO> getAllOrders();

    void deleteOrder(Long id);
}
