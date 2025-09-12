package com.spring_batch.shopping_mall.order.controller;

import com.spring_batch.shopping_mall.order.dto.OrderRequest;
import com.spring_batch.shopping_mall.order.dto.OrderResponse;
import com.spring_batch.shopping_mall.order.service.OrderService;
import com.spring_batch.shopping_mall.payment.dto.PaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 관련 API 컨트롤러
 */
@RestController
@Tag(name = "2. 주문 관리 API")
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "주문 생성")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return OrderResponse.from(orderService.order(orderRequest.getCustomerId(),
                orderRequest.toOrderItemCommands(), orderRequest.getPaymentMethod()));
    }


    @Operation(summary = "결재 생성")
    @PostMapping("/{orderId}/payment")
    public OrderResponse completePayment(@PathVariable Long orderId,
            @RequestBody PaymentRequest paymentRequest) {
        return OrderResponse.from(
                orderService.completePayment(orderId, paymentRequest.isSuccess()));
    }

    @Operation(summary = "주문 완료 처리")
    @PostMapping("/{orderId}/complete")
    public OrderResponse completeOrder(@PathVariable Long orderId) {
        return OrderResponse.from(orderService.completeOrder(orderId));
    }

    @Operation(summary = "주문 취소")
    @PostMapping("/{orderId}/cancel")
    public OrderResponse cancelOrder(@PathVariable Long orderId) {
        return OrderResponse.from(orderService.cancelOrder(orderId));
    }
}