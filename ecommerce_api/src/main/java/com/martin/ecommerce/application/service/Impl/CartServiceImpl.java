package com.martin.ecommerce.application.service.Impl;

import com.martin.ecommerce.application.service.CartService;
import com.martin.ecommerce.domain.dto.cart.CartItemRequest;
import com.martin.ecommerce.domain.dto.cart.CartResponse;
import com.martin.ecommerce.domain.dto.cart.RemoveItemRequest;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;
import com.martin.ecommerce.domain.exception.OutStockException;
import com.martin.ecommerce.domain.exception.ResourceNotFoundException;
import com.martin.ecommerce.domain.mapper.CartMapper;
import com.martin.ecommerce.domain.model.Cart;
import com.martin.ecommerce.domain.model.CartItem;
import com.martin.ecommerce.domain.model.Product;
import com.martin.ecommerce.domain.model.User;
import com.martin.ecommerce.infrastructure.repository.CartItemRepository;
import com.martin.ecommerce.infrastructure.repository.CartRepository;
import com.martin.ecommerce.infrastructure.repository.ProductRepository;
import com.martin.ecommerce.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final CartItemRepository cartItemRepository;

  @Transactional
  @Override
  public CommonResponse<CartResponse> addToCart(Long userId, CartItemRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Product product = productRepository.findById(request.productId())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    if (product.getStock() < request.quantity()) {
      throw new OutStockException("Product out of stock");
    }

    Cart cart = cartRepository.findByUserId(userId)
        .orElse(new Cart(null, user, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now()));

    Optional<CartItem> existingCartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(request.productId()))
        .findFirst();

    if (existingCartItem.isPresent()) {
      CartItem cartItem = existingCartItem.get();
      cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
      cartItemRepository.save(cartItem);
    } else {
      CartItem cartItem = new CartItem(null, request.quantity(),
          request.quantity() * product.getPrice(), cart, product);
      CartItem savedCartItem = cartItemRepository.save(cartItem);
      cart.getItems().add(savedCartItem);
    }

    Cart savedCart = cartRepository.save(cart);
    return new CommonResponse<>(
        "Cart added successfully",
        CartMapper.toCartResponse(savedCart)
    );
  }

  @Override
  public CartResponse getCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    return CartMapper.toCartResponse(cart);
  }

  @Transactional
  @Override
  public DeleteResponse clearCart(Long userId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    cart.getItems().clear();
    cartRepository.save(cart);
    return new DeleteResponse("Cart cleared successfully");
  }

  @Transactional
  @Override
  public DeleteResponse removeItem(Long userId, RemoveItemRequest request) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    cart.getItems().removeIf(item -> item.getProduct().getId().equals(request.productId()));
    cartRepository.save(cart);
    return new DeleteResponse("Item removed successfully");
  }
}
