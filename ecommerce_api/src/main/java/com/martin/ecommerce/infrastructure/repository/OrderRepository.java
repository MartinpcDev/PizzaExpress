package com.martin.ecommerce.infrastructure.repository;

import com.martin.ecommerce.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
