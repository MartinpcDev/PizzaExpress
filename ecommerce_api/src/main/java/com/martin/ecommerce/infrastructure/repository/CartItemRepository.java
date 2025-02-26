package com.martin.ecommerce.infrastructure.repository;

import com.martin.ecommerce.domain.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  
}
