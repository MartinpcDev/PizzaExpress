package com.martin.ecommerce.application.service;

import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;
import com.martin.ecommerce.domain.dto.auth.ProfileResponse;
import com.martin.ecommerce.domain.dto.auth.RefreshRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterResponse;
import com.martin.ecommerce.domain.dto.common.MessageResponse;
import com.martin.ecommerce.domain.dto.email.ConfirmationRequest;

public interface AuthService {

  LoginResponse login(LoginRequest loginRequest);

  RegisterResponse register(RegisterRequest registerRequest);

  ProfileResponse profile(Long userId);

  LoginResponse refresh(RefreshRequest refreshRequest);

  MessageResponse confirmEmail(ConfirmationRequest confirmationRequest);
}
