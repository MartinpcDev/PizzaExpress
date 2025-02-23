package com.martin.ecommerce.domain.dto.product;

public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock,
    String image
) {

}
