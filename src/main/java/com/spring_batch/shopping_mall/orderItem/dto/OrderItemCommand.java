package com.spring_batch.shopping_mall.orderItem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemCommand {

  private String productId;
  private int quantity;
}
