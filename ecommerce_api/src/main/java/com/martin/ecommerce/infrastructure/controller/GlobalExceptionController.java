package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.domain.dto.common.ExceptionResponse;
import com.martin.ecommerce.domain.exception.DuplicatedResourceException;
import com.martin.ecommerce.domain.exception.InvalidAuthException;
import com.martin.ecommerce.domain.exception.OutStockException;
import com.martin.ecommerce.domain.exception.ResourceNotFoundException;
import com.martin.ecommerce.domain.exception.UserNotFoundExcetion;
import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getAllErrors();
    List<String> details = errors.stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .error("Bad Request")
        .message("Validation failed")
        .details(details)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(UserNotFoundExcetion.class)
  public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundExcetion e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .error(e.getMessage())
        .message("User not found")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
      ResourceNotFoundException e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .error(e.getMessage())
        .message("Resource not found")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(OutStockException.class)
  public ResponseEntity<ExceptionResponse> handleOutStockException(OutStockException e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .error(e.getMessage())
        .message("Out of stock")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(InvalidAuthException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidAuthException(InvalidAuthException e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.UNAUTHORIZED.value())
        .error(e.getMessage())
        .message("Invalid authentication")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(DuplicatedResourceException.class)
  public ResponseEntity<ExceptionResponse> handleDuplicatedResourceException(
      DuplicatedResourceException e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.CONFLICT.value())
        .error(e.getMessage())
        .message("Duplicated resource")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
      IllegalArgumentException e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .error(e.getMessage())
        .message("Invalid argument")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(Exception e) {
    ExceptionResponse response = ExceptionResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .error(e.getMessage())
        .message("An error occurred")
        .details(null)
        .build();
    return ResponseEntity.status(response.statusCode()).body(response);
  }
}
