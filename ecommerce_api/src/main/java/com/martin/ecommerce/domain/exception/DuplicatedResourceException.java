package com.martin.ecommerce.domain.exception;

public class DuplicatedResourceException extends RuntimeException {

  public DuplicatedResourceException(String message) {
    super(message);
  }
}
