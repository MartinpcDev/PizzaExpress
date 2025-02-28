package com.martin.ecommerce.domain.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConfirmationRequest(
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    String email,
    @NotBlank(message = "Token is required")
    String token
) {

}
