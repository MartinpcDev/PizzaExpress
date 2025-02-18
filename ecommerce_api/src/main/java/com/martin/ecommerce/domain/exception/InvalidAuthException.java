package com.martin.ecommerce.domain.exception;

public class InvalidAuthException extends RuntimeException {

  public InvalidAuthException(String message) {
    super(message);
  }
}
