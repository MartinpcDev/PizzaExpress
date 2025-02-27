package com.martin.ecommerce.application.service;

import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.order.OrderResponse;
import java.util.List;

public interface OrderService {

  CommonResponse<OrderResponse> createOrder(Long userId, String address, String phoneNumber);

  List<OrderResponse> getAllOrders();

  List<OrderResponse> getOrdersByUserId(Long userId);

  OrderResponse updateOrderStatus(Long orderId, String status);
}
