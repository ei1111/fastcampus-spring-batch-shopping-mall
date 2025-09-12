package com.spring_batch.shopping_mall.order.service;

import com.spring_batch.shopping_mall.order.domain.Order;
import com.spring_batch.shopping_mall.order.dto.OrderResult;
import com.spring_batch.shopping_mall.order.repository.OrderRepository;
import com.spring_batch.shopping_mall.orderItem.domain.OrderItem;
import com.spring_batch.shopping_mall.orderItem.dto.OrderItemCommand;
import com.spring_batch.shopping_mall.payment.domain.PaymentMethod;
import com.spring_batch.shopping_mall.product.dto.ProductResult;
import com.spring_batch.shopping_mall.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public OrderResult order(Long customerId, List<OrderItemCommand> orderItems,
            PaymentMethod paymentMethod) {
        Order order = Order.createOrder(customerId);
        for (OrderItemCommand item : orderItems) {
            ProductResult product = productService.findProduct(item.getProductId());
            order.addOrderItem(product.getProductId(), item.getQuantity(),
                    product.getSalesPrice());
        }
        order.initPayment(paymentMethod);
        return save(order);
    }

    private OrderResult save(Order order) {
        return OrderResult.from(orderRepository.save(order));
    }


    @Transactional
    public OrderResult completePayment(Long orderId, boolean isSuccess) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found"));
        order.completePayment(isSuccess);
        decreaseStock(isSuccess, order);
        return save(order);
    }

    private void decreaseStock(boolean isSuccess, Order order) {
        if (isSuccess) {
            for (OrderItem orderItem : order.getOrderItems()) {
                productService.decreaseStock(orderItem.getProductId(), orderItem.getQuantity());
            }
        }
    }


    @Transactional
    public OrderResult completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found"));
        order.completeOrder();
        return save(order);
    }

    @Transactional
    public OrderResult cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found"));
        order.cancel();
        recoverStock(order);
        return save(order);
    }

    private void recoverStock(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            productService.increaseStock(orderItem.getProductId(), orderItem.getQuantity());
        }
    }
}