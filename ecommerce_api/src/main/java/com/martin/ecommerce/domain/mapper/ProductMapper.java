package com.martin.ecommerce.domain.mapper;

import com.martin.ecommerce.domain.dto.product.ProductRequest;
import com.martin.ecommerce.domain.dto.product.ProductResponse;
import com.martin.ecommerce.domain.model.Product;
import java.util.List;

public class ProductMapper {

  public static ProductResponse toProductResponse(Product product) {
    if (product == null) {
      return null;
    }

    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getStock(),
        product.getImage()
    );
  }

  public static List<ProductResponse> toProductResponseList(List<Product> products) {
    if (products == null) {
      return null;
    }

    return products.stream()
        .map(ProductMapper::toProductResponse)
        .toList();
  }

  public static Product toProduct(ProductRequest productRequest) {
    if (productRequest == null) {
      return null;
    }

    Product product = new Product();
    product.setName(productRequest.name());
    product.setDescription(productRequest.description());
    product.setPrice(productRequest.price());
    product.setStock(productRequest.stock());
    product.setImage(productRequest.image());

    return product;
  }

  public static void updateProduct(Product product, ProductRequest productRequest) {
    if (product != null && productRequest != null) {
      if (productRequest.name() != null) {
        product.setName(productRequest.name());
      }
      if (productRequest.description() != null) {
        product.setDescription(productRequest.description());
      }
      if (productRequest.price() != null) {
        product.setPrice(productRequest.price());
      }
      if (productRequest.stock() != null) {
        product.setStock(productRequest.stock());
      }
      if (productRequest.image() != null) {
        product.setImage(productRequest.image());
      }
    }
  }
}
