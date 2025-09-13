package com.spring_batch.shopping_mall.transaction.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionType {
    ORDER_CREATION("주문생성"),
    PAYMENT_COMPLETION("결제완료"),
    ORDER_COMPLETION("주문완료"),
    ORDER_CANCELLATION("주문취소");

    private final String desc;
}
