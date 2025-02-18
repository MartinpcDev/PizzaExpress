package com.martin.ecommerce.application.interfaces;

import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;

public interface AuthService {

  LoginResponse login(LoginRequest loginRequest);
}
