package com.martin.ecommerce.application.service;

import com.martin.ecommerce.application.interfaces.AuthService;
import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    return new LoginResponse(
        "Login successful for user: ",
        loginRequest.email(),
        loginRequest.name());
  }
}
