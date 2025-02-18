package com.martin.ecommerce.domain.dto.auth;

public record LoginRequest(
    String name,
    String username,
    String email,
    String password
) {

}
