package com.martin.ecommerce.application.service;

import com.martin.ecommerce.domain.dto.cart.CartItemRequest;
import com.martin.ecommerce.domain.dto.cart.CartResponse;
import com.martin.ecommerce.domain.dto.cart.RemoveItemRequest;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;

public interface CartService {

  CommonResponse<CartResponse> addToCart(Long userId, CartItemRequest request);

  CartResponse getCart(Long userId);

  DeleteResponse clearCart(Long userId);

  DeleteResponse removeItem(Long userId, RemoveItemRequest request);
}
