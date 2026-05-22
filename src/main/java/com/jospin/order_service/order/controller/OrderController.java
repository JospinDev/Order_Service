package com.jospin.order_service.order.controller;

import com.jospin.order_service.order.dto.OrderRequestDTO;
import com.jospin.order_service.order.dto.OrderResponseDTO;
import com.jospin.order_service.order.service.OrderService;
import com.jospin.order_service.common.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(
            @RequestBody OrderRequestDTO dto) {

        OrderResponseDTO order = orderService.createOrder(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order created successfully", order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(
            @PathVariable Long id) {

        OrderResponseDTO order = orderService.getOrderById(id);

        return ResponseEntity.ok(
                ApiResponse.success("Order retrieved successfully", order)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getAllOrders() {

        List<OrderResponseDTO> orders = orderService.getAllOrders();

        return ResponseEntity.ok(
                ApiResponse.success("All orders retrieved", orders)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok(
                ApiResponse.success("Order deleted successfully")
        );
    }
}