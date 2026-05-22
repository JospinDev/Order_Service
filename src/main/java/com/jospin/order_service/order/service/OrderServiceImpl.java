package com.jospin.order_service.order.service;

import com.jospin.order_service.order.entity.Order;
import com.jospin.order_service.order.mapper.OrderMapper;
import com.jospin.order_service.order.repository.OrderRepository;
import com.jospin.order_service.order.validations.Validator;
import com.jospin.order_service.common.ApiResponse;
import com.jospin.order_service.order.dto.OrderRequestDTO;
import com.jospin.order_service.order.dto.OrderResponseDTO;
import com.jospin.order_service.order_item.entity.OrderItem;
import com.jospin.order_service.user.dto.UserResponseDTO;
import com.jospin.order_service.user.service.UserClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final Validator validator;
    private final UserClient userClient;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto){

        validator.validateUserExists(dto.getUserId());

        validator.validateOrderItems(dto.getItems());

        Order order = orderMapper.toEntity(dto);

        List<OrderItem> items = dto.getItems()
                .stream()
                .map(i -> OrderItem.builder()
                        .productName(i.getProductName())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
                        .order(order)
                        .build())
                .toList();

        double total =
                dto.getItems()
                        .stream()
                        .mapToDouble(
                            i->i.getPrice()*i.getQuantity()
                        )
                        .sum();

        order.setItems(items);
        order.setTotalPrice(total);

        Order saved =
                orderRepository.save(order);

        OrderResponseDTO response =
                orderMapper.toResponseDTO(saved);

        ApiResponse<UserResponseDTO> userResponse =
                userClient.getUserById(saved.getUserId());

        response.setUser(
                userResponse.getData()
        );

        return response;
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {

        Order order = validator.validateOrderExist(id);

        OrderResponseDTO response =
                orderMapper.toResponseDTO(order);

        ApiResponse<UserResponseDTO> userResponse =
                userClient.getUserById(
                        order.getUserId()
                );

        response.setUser(
                userResponse.getData()
        );

        return response;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepository.delete(order);
    }
}