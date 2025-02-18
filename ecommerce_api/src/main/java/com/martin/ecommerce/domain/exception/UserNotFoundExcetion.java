package com.martin.ecommerce.domain.exception;

public class UserNotFoundExcetion extends RuntimeException {

  public UserNotFoundExcetion(String message) {
    super(message);
  }
}
