package com.martin.ecommerce.infrastructure.repository;

import com.martin.ecommerce.domain.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserId(Long userId);

  void deleteByUserId(Long userId);
}
