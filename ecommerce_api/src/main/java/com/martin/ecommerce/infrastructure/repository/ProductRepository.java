package com.martin.ecommerce.infrastructure.repository;

import com.martin.ecommerce.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByNameIgnoreCase(String name);

  Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
