package com.martin.ecommerce.domain.dto.cart;

import com.martin.ecommerce.domain.dto.product.ProductResponse;

public record CartItemResponse(
    Long id,
    Integer quantity,
    Double total,
    ProductResponse product
) {

}
