package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.application.service.CartService;
import com.martin.ecommerce.application.utils.UserUtils;
import com.martin.ecommerce.domain.dto.cart.CartItemRequest;
import com.martin.ecommerce.domain.dto.cart.CartResponse;
import com.martin.ecommerce.domain.dto.cart.RemoveItemRequest;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CartController {

  private final CartService cartService;

  @GetMapping
  public ResponseEntity<CartResponse> getCart(@AuthenticationPrincipal UserDetails userDetails) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(cartService.getCart(userId));
  }

  @PostMapping
  public ResponseEntity<CommonResponse<CartResponse>> addToCart(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid CartItemRequest request
  ) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cartService.addToCart(userId, request));
  }

  @DeleteMapping("/clear-cart")
  public ResponseEntity<DeleteResponse> clearCart(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(cartService.clearCart(userId));
  }

  @DeleteMapping("/remove-item")
  public ResponseEntity<DeleteResponse> removeItem(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid RemoveItemRequest request
  ) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(cartService.removeItem(userId, request));
  }
}
