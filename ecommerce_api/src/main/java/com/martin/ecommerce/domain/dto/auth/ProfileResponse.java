package com.martin.ecommerce.domain.dto.auth;

public record ProfileResponse(
    Long id,
    String name,
    String username,
    String email,
    String role
) {

}
