package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CartController {

  private final CartService cartService;
}
