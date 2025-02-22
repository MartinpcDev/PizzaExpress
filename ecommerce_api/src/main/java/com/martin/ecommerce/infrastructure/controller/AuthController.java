package com.martin.ecommerce.infrastructure.controller;

import com.martin.ecommerce.application.interfaces.AuthService;
import com.martin.ecommerce.application.utils.UserUtils;
import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;
import com.martin.ecommerce.domain.dto.auth.ProfileResponse;
import com.martin.ecommerce.domain.dto.auth.RefreshRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
      @RequestBody @Valid LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(
      @RequestBody @Valid RegisterRequest registerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(registerRequest));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<LoginResponse> refreshToken(
      @RequestBody @Valid RefreshRequest refreshRequest) {
    return ResponseEntity.ok(authService.refresh(refreshRequest));
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/profile")
  public ResponseEntity<ProfileResponse> profile(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = UserUtils.extractId(userDetails);
    return ResponseEntity.ok(authService.profile(userId));
  }
}
