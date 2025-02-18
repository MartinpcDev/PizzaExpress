package com.martin.ecommerce.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
    String message,
    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("refresh_token")
    String refreshToken
) {

}
