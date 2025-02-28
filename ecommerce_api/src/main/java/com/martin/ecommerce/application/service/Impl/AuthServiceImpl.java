package com.martin.ecommerce.application.service.Impl;

import com.martin.ecommerce.application.service.AuthService;
import com.martin.ecommerce.application.service.EmailService;
import com.martin.ecommerce.application.utils.JwtUtils;
import com.martin.ecommerce.domain.dto.auth.LoginRequest;
import com.martin.ecommerce.domain.dto.auth.LoginResponse;
import com.martin.ecommerce.domain.dto.auth.ProfileResponse;
import com.martin.ecommerce.domain.dto.auth.RefreshRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterRequest;
import com.martin.ecommerce.domain.dto.auth.RegisterResponse;
import com.martin.ecommerce.domain.dto.common.MessageResponse;
import com.martin.ecommerce.domain.dto.email.ConfirmationRequest;
import com.martin.ecommerce.domain.exception.InvalidAuthException;
import com.martin.ecommerce.domain.model.User;
import com.martin.ecommerce.infrastructure.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final EmailService emailService;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    User userExists = userRepository.findByUsernameIgnoreCase(loginRequest.username())
        .orElseThrow(() -> new InvalidAuthException("Invalid username"));

    if (!passwordEncoder.matches(loginRequest.password(), userExists.getPassword())) {
      throw new InvalidAuthException("Invalid password");
    }

    if (!userExists.isEmailConfirmation()) {
      throw new InvalidAuthException("Please confirm your email");
    }

    String accessToken = jwtUtils.generateAccessToken(userExists);
    String refreshToken = jwtUtils.generateRefreshToken(userExists);

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
    );

    return new LoginResponse("Login successful", accessToken, refreshToken);
  }

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {
    if (userRepository.existsByEmailIgnoreCase(registerRequest.email())
        || userRepository.existsByUsernameIgnoreCase(registerRequest.username())) {
      throw new InvalidAuthException("User already exists");
    }

    User user = new User();
    user.setEmail(registerRequest.email());
    user.setName(registerRequest.name());
    user.setUsername(registerRequest.username());
    user.setPassword(passwordEncoder.encode(registerRequest.password()));
    user.setEmailConfirmation(false);
    user.setConfirmationCode(generateConfirmationCode());
    emailService.sendConfirmationCode(user);

    userRepository.save(user);

    return new RegisterResponse("User registered successfully");
  }

  @Override
  public ProfileResponse profile(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new InvalidAuthException("User not found"));
    return new ProfileResponse(user.getId(), user.getName(), user.getUsername(), user.getEmail(),
        user.getRole().name());
  }

  @Override
  public LoginResponse refresh(RefreshRequest refreshRequest) {
    String refreshToken = refreshRequest.refreshToken();
    String username = jwtUtils.extractUsername(refreshToken);
    User user = userRepository.findByUsernameIgnoreCase(username)
        .orElseThrow(() -> new InvalidAuthException("User not found"));

    if (!jwtUtils.isTokenValid(refreshToken, user)) {
      throw new InvalidAuthException("Invalid token");
    }

    String newAccessToken = jwtUtils.generateAccessToken(user);

    return new LoginResponse("Token refreshed", newAccessToken, refreshToken);
  }

  @Override
  public MessageResponse confirmEmail(ConfirmationRequest confirmationRequest) {
    User user = userRepository.findByEmailIgnoreCase(confirmationRequest.email())
        .orElseThrow(() -> new InvalidAuthException("User not found"));

    if (!user.getConfirmationCode().equals(confirmationRequest.token())) {
      throw new InvalidAuthException("Invalid confirmation code");
    }

    user.setEmailConfirmation(true);
    user.setConfirmationCode(null);
    userRepository.save(user);
    
    return new MessageResponse("Email confirmed successfully");
  }

  private String generateConfirmationCode() {
    return UUID.randomUUID().toString();
  }
}
