package com.martin.ecommerce.application.service;

import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;
import com.martin.ecommerce.domain.dto.common.PaginationResponse;
import com.martin.ecommerce.domain.dto.product.ProductRequest;
import com.martin.ecommerce.domain.dto.product.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  PaginationResponse<ProductResponse> findAll(Pageable pageable);

  PaginationResponse<ProductResponse> searchByName(String name, Pageable pageable);

  ProductResponse findById(Long productId);

  CommonResponse<ProductResponse> save(ProductRequest productRequest);

  CommonResponse<ProductResponse> update(Long productId, ProductRequest productRequest);

  DeleteResponse delete(Long productId);
}
