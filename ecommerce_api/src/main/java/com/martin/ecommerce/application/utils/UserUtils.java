package com.martin.ecommerce.application.utils;

import com.martin.ecommerce.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

  public static Long extractId(UserDetails userDetails){
    return ((User) userDetails).getId();
  }
}
