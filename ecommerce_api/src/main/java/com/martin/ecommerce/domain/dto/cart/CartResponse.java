package com.martin.ecommerce.domain.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record CartResponse(
    Long id,
    List<CartItemResponse> items,
    @JsonProperty("created_at")
    LocalDateTime createdAt,
    @JsonProperty("updated_at")
    LocalDateTime updatedAt
) {

}
