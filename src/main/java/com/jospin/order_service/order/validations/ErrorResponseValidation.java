package com.jospin.order_service.order.validations;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseValidation {

    private String message;
    private ValidationErrorType type;
    private LocalDateTime timestamp;

    private String path;
    private Integer status;
}
