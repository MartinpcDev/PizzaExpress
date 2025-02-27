package com.martin.ecommerce.domain.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.ecommerce.domain.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    OrderStatus status,
    List<OrderItemResponse> items,
    @JsonProperty("created_at")
    LocalDateTime createdAt
) {

}
