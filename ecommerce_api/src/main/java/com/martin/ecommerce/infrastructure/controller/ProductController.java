package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.application.service.ProductService;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;
import com.martin.ecommerce.domain.dto.common.PaginationResponse;
import com.martin.ecommerce.domain.dto.product.ProductRequest;
import com.martin.ecommerce.domain.dto.product.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<PaginationResponse<ProductResponse>> findAll(
      @PageableDefault(sort = {"id"}) Pageable pageable) {
    return ResponseEntity.ok(productService.findAll(pageable));
  }

  @GetMapping("/search")
  public ResponseEntity<PaginationResponse<ProductResponse>> searchByName(
      @RequestParam String name,
      @PageableDefault(sort = {"id"}) Pageable pageable) {
    return ResponseEntity.ok(productService.searchByName(name, pageable));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> findById(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.findById(productId));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<CommonResponse<ProductResponse>> save(
      @RequestBody @Valid ProductRequest productRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.save(productRequest));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{productId}")
  public ResponseEntity<CommonResponse<ProductResponse>> update(
      @PathVariable Long productId,
      @RequestBody @Valid ProductRequest productRequest) {
    return ResponseEntity.ok(productService.update(productId, productRequest));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{productId}")
  public ResponseEntity<DeleteResponse> delete(@PathVariable Long productId) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(productService.delete(productId));
  }
}
