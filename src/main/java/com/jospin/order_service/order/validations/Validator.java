package com.jospin.order_service.order.validations;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jospin.order_service.common.ApiResponse;
import com.jospin.order_service.order.entity.Order;
import com.jospin.order_service.order.repository.OrderRepository;
import com.jospin.order_service.order_item.dto.OrderItemDTO;
import com.jospin.order_service.user.dto.UserResponseDTO;
import com.jospin.order_service.user.service.UserClient;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Validator {

    private final UserClient userClient;
    private final OrderRepository orderRepository;

    public void validateUserExists(Long userId){

        try {

            ApiResponse<UserResponseDTO> response =
                    userClient.getUserById(userId);

            if(response.getData()==null){

                throw new ValidationException(
                        "User not found with id " + userId,
                        ValidationErrorType.NOT_FOUND
                );
            }

        } catch (FeignException.NotFound e){

            throw new ValidationException(
                    "User not found with id " + userId,
                    ValidationErrorType.NOT_FOUND
            );
        }
    }

    public void validateOrderItems(List<OrderItemDTO> items) {

        if (items == null || items.isEmpty()) {
            throw new ValidationException(
                    "Order must contain at least one item",
                    ValidationErrorType.INVALID_REQUEST
            );
        }
    }

     public Order validateOrderExist(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ValidationException(
                                "Order not found with id " + id,
                                ValidationErrorType.NOT_FOUND
                        )
                );
     }

}
