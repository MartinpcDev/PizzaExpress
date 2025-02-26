package com.martin.ecommerce.domain.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequest(
    @JsonProperty("product_id")
    @NotNull(message = "Product ID is required")
    Long productId,
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    Integer quantity
) {

}
