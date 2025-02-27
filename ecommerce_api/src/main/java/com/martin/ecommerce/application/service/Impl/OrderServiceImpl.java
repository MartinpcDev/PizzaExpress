package com.martin.ecommerce.application.service.Impl;

import com.martin.ecommerce.application.service.CartService;
import com.martin.ecommerce.application.service.OrderService;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.order.OrderResponse;
import com.martin.ecommerce.infrastructure.repository.OrderRepository;
import com.martin.ecommerce.infrastructure.repository.ProductRepository;
import com.martin.ecommerce.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final CartService cartService;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Transactional
  @Override
  public CommonResponse<OrderResponse> createOrder(Long userId, String address,
      String phoneNumber) {
    return null;
  }

  @Override
  public List<OrderResponse> getAllOrders() {
    return List.of();
  }

  @Override
  public List<OrderResponse> getOrdersByUserId(Long userId) {
    return List.of();
  }

  @Transactional
  @Override
  public OrderResponse updateOrderStatus(Long orderId, String status) {
    return null;
  }
}
