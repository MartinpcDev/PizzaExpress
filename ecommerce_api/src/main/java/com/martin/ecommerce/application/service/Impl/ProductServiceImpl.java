package com.martin.ecommerce.application.service.Impl;

import com.martin.ecommerce.application.service.ProductService;
import com.martin.ecommerce.domain.dto.common.CommonResponse;
import com.martin.ecommerce.domain.dto.common.DeleteResponse;
import com.martin.ecommerce.domain.dto.common.PaginationResponse;
import com.martin.ecommerce.domain.dto.product.ProductRequest;
import com.martin.ecommerce.domain.dto.product.ProductResponse;
import com.martin.ecommerce.domain.exception.DuplicatedResourceException;
import com.martin.ecommerce.domain.exception.ResourceNotFoundException;
import com.martin.ecommerce.domain.mapper.PaginationMapper;
import com.martin.ecommerce.domain.mapper.ProductMapper;
import com.martin.ecommerce.domain.model.Product;
import com.martin.ecommerce.infrastructure.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public PaginationResponse<ProductResponse> findAll(Pageable pageable) {
    Page<Product> productPage = productRepository.findAll(pageable);
    return PaginationMapper.toPaginationResponse(
        productPage.getContent(),
        productPage,
        ProductMapper::toProductResponse
    );
  }

  @Override
  public PaginationResponse<ProductResponse> searchByName(String name, Pageable pageable) {
    Page<Product> productPage = productRepository.findAllByNameContainingIgnoreCase(name, pageable);
    return PaginationMapper.toPaginationResponse(
        productPage.getContent(),
        productPage,
        ProductMapper::toProductResponse
    );
  }

  @Override
  public ProductResponse findById(Long productId) {
    Product product = this.findProductById(productId);
    return ProductMapper.toProductResponse(product);
  }

  @Transactional
  @Override
  public CommonResponse<ProductResponse> save(ProductRequest productRequest) {
    if (productRepository.existsByNameIgnoreCase(productRequest.name())) {
      throw new DuplicatedResourceException(
          "Product with name " + productRequest.name() + " already exists");
    }
    Product product = ProductMapper.toProduct(productRequest);
    Product savedProduct = productRepository.save(product);
    return new CommonResponse<>("Product created", ProductMapper.toProductResponse(savedProduct));
  }

  @Transactional
  @Override
  public CommonResponse<ProductResponse> update(Long productId, ProductRequest productRequest) {
    Product product = this.findProductById(productId);

    if (productRepository.existsByNameIgnoreCase(productRequest.name()) &&
        !Objects.equals(productRequest.name(), product.getName())) {
      throw new DuplicatedResourceException(
          "Product with name " + productRequest.name() + " already exists");
    }

    ProductMapper.updateProduct(product, productRequest);

    return new CommonResponse<>("Product updated", ProductMapper.toProductResponse(product));
  }

  @Transactional
  @Override
  public DeleteResponse delete(Long productId) {
    Product product = this.findProductById(productId);
    productRepository.delete(product);
    return new DeleteResponse("Product deleted");
  }

  private Product findProductById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not "
            + "found"));
  }
}
