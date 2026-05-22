package com.jospin.order_service.order_item.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private String productName;
    private Integer quantity;
    private Double price;
}
