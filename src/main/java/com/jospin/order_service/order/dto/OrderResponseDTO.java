package com.jospin.order_service.order.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.jospin.order_service.order_item.dto.OrderItemDTO;
import com.jospin.order_service.user.dto.UserResponseDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long id;
    private UserResponseDTO user;
    private String status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;
}
