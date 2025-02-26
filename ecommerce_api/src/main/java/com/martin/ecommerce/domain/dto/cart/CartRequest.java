package com.martin.ecommerce.domain.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CartRequest(
    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    Long userId
) {

}
