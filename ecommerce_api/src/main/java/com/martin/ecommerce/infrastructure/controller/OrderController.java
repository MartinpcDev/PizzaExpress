package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrderController {

  private final OrderService orderService;
}
