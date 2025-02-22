package com.martin.ecommerce.application.interfaces;

import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;
import com.martin.ecommerce.domain.dto.auth.ProfileResponse;
import com.martin.ecommerce.domain.dto.auth.RefreshRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterResponse;

public interface AuthService {

  LoginResponse login(LoginRequest loginRequest);

  RegisterResponse register(RegisterRequest registerRequest);

  ProfileResponse profile(Long userId);

  LoginResponse refresh(RefreshRequest refreshRequest);
}
