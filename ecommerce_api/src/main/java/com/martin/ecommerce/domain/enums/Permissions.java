package com.martin.ecommerce.domain.enums;

public enum Permissions {
  // Productos
  PRODUCT_READ,
  PRODUCT_SAVE,
  PRODUCT_UPDATE,
  PRODUCT_DELETE,

  // Compras/Ordenes
  ORDER_READ,
  ORDER_CREATE,
  ORDER_UPDATE,
  ORDER_DELETE,

  // Usuarios (solo Admins)
  USER_MANAGE
}
