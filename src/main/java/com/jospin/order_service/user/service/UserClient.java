package com.jospin.order_service.user.service;

import com.jospin.order_service.common.ApiResponse;
import com.jospin.order_service.user.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8080/user_service")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    ApiResponse<UserResponseDTO> getUserById(@PathVariable Long id);
}
