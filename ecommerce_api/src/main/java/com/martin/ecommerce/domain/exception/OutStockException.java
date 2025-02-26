package com.martin.ecommerce.domain.exception;

public class OutStockException extends RuntimeException {

  public OutStockException(String message) {
    super(message);
  }
}
