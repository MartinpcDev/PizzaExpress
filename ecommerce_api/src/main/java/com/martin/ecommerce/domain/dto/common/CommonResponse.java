package com.martin.ecommerce.domain.dto.common;

public record CommonResponse<T>(
    String message,
    T content
) {

}
