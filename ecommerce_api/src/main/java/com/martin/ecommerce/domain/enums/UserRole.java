package com.martin.ecommerce.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
  ADMIN(Arrays.asList(Permissions.values())),
  USER(Arrays.asList(
      Permissions.PRODUCT_READ,
      Permissions.ORDER_READ,
      Permissions.ORDER_CREATE,
      Permissions.ORDER_UPDATE,
      Permissions.ORDER_DELETE));

  private final List<Permissions> permissions;

  UserRole(List<Permissions> permissions) {
    this.permissions = permissions;
  }

  public List<Permissions> getPermissions() {
    return permissions;
  }

}
