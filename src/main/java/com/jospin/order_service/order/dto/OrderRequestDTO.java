package com.jospin.order_service.order.dto;

import lombok.*;
import java.util.List;

import com.jospin.order_service.order_item.dto.OrderItemDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private Long userId;
    private List<OrderItemDTO> items;
}
