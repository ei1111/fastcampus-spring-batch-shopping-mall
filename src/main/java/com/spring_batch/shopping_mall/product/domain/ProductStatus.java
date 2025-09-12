package com.spring_batch.shopping_mall.product.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductStatus {
    AVAILABLE("판매중"),
    OUT_OF_STOCK("품절"),
    DISCONTINUED("판매종료");

    private final String desc;
}
