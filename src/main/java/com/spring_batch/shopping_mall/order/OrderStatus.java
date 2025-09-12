package com.spring_batch.shopping_mall.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    PENDING_PAYMENT("결제 대기 중"),
    PROCESSING("처리 중"),
    COMPLETED("완료됨"),
    CANCELLED("취소됨");

    final String desc;
}
