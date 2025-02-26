package com.martin.ecommerce.domain.mapper;

import com.martin.ecommerce.domain.dto.cart.CartItemResponse;
import com.martin.ecommerce.domain.dto.cart.CartResponse;
import com.martin.ecommerce.domain.model.Cart;
import com.martin.ecommerce.domain.model.CartItem;
import java.util.List;

public class CartMapper {

  public static CartResponse toCartResponse(Cart cart) {
    if (cart == null) {
      return null;
    }

    return new CartResponse(
        cart.getId(),
        toCartItemResponseList(cart.getItems()),
        cart.getCreatedAt(),
        cart.getUpdatedAt()
    );
  }

  public static CartItemResponse toCartItemResponse(CartItem cartItem) {
    if (cartItem == null) {
      return null;
    }

    return new CartItemResponse(
        cartItem.getId(),
        cartItem.getQuantity(),
        cartItem.getTotal(),
        ProductMapper.toProductResponse(cartItem.getProduct())
    );
  }

  public static List<CartItemResponse> toCartItemResponseList(List<CartItem> cartItems) {
    return cartItems.stream()
        .map(CartMapper::toCartItemResponse)
        .toList();
  }
}
