package com.martin.ecommerce.infrastructure.repository;

import com.martin.ecommerce.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  
}
