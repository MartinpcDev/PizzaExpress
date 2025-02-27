package com.martin.ecommerce.application.service;

import com.martin.ecommerce.domain.model.Order;
import com.martin.ecommerce.domain.model.User;

public interface EmailService {

  void sendOrderConfirmation(Order order);

  void sendConfirmationCode(User user);
}
