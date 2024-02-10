package com.rshop.ecomservice.dto;

import com.rshop.ecomservice.domain.Product;
import lombok.Data;

@Data
public class OrderProductDto {
  private long productId;
  private int quantity;
}