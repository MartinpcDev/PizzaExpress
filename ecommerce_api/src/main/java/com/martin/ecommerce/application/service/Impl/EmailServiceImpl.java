package com.martin.ecommerce.application.service.Impl;

import com.martin.ecommerce.application.service.EmailService;
import com.martin.ecommerce.domain.model.Order;
import com.martin.ecommerce.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String fromEmail;

  @Override
  public void sendOrderConfirmation(Order order) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(fromEmail);
    message.setTo(order.getUser().getEmail());
    message.setSubject("Order Confirmation");
    message.setText("Your order has been confirmed. Order ID: " + order.getId());
    mailSender.send(message);
  }

  @Override
  public void sendConfirmationCode(User user) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(fromEmail);
    message.setTo(user.getEmail());
    message.setSubject("Confirm your email");
    message.setText(
        "Please confirm your email by entering the code: " + user.getConfirmationCode());
    mailSender.send(message);
  }
}
