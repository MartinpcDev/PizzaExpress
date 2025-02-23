package com.martin.ecommerce.domain.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record ProductRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    String name,
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    String description,
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price,
    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be greater than zero")
    Integer stock,
    @NotBlank(message = "Image is required")
    @URL(message = "Image must be a valid URL")
    String image
) {

}
