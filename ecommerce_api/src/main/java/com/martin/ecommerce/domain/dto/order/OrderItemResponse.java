package com.martin.ecommerce.domain.dto.order;

import com.martin.ecommerce.domain.dto.product.ProductResponse;

public record OrderItemResponse(
    Long id,
    Integer quantity,
    Double total,
    ProductResponse product
) {

}
