package com.spring_batch.shopping_mall.order.dto;

import com.spring_batch.shopping_mall.orderItem.dto.OrderItemCommand;
import com.spring_batch.shopping_mall.orderItem.dto.OrderItemRequest;
import com.spring_batch.shopping_mall.payment.domain.PaymentMethod;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderRequest {

    private Long customerId;
    private List<OrderItemRequest> orderItems;
    private PaymentMethod paymentMethod;

    public static OrderRequest of(Long customerId, List<OrderItemRequest> orderItems,
            PaymentMethod paymentMethod) {
        return new OrderRequest(customerId, orderItems, paymentMethod);
    }

    public List<OrderItemCommand> toOrderItemCommands() {
        return orderItems.stream()
                .map(item -> new OrderItemCommand(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());
    }
}