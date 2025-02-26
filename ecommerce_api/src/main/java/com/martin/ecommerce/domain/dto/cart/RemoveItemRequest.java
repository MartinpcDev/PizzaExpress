package com.martin.ecommerce.domain.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RemoveItemRequest(
    @JsonProperty("product_id")
    @NotNull(message = "Product ID is required")
    Long productId
) {

}
