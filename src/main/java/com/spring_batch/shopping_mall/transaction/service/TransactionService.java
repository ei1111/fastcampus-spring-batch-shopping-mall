package com.spring_batch.shopping_mall.transaction.service;

import com.spring_batch.shopping_mall.order.dto.OrderResult;
import com.spring_batch.shopping_mall.transaction.domain.TransactionStatus;
import com.spring_batch.shopping_mall.transaction.domain.TransactionType;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    protected static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public void logTransaction(TransactionType transactionType, TransactionStatus transactionStatus, String message, OrderResult order) {
        try {
            putMdc(transactionType, transactionStatus, order);
            log(transactionStatus, message);
        } finally {
            MDC.clear();
        }
    }

    /*Mdc(Mapped Diagnostic Context) -> logback이나 Slf4j에서 사용하는 기술이다.*
      로깅 메시지의 추가적인 진단을 쉽게 추가 할 수 있게 해준다.
      쓰레드 기반의 로그 추적이나 컨텍스트 정보를 로그에 포함될때 사용
      Spring App처럼 멀티 쓰레드 환경에서 특정 요청 작업을 추적할때 유용하다.
      Mdc는 쓰레드 로컬 저장소를 사용하기 때문에 각 쓰레드마다 독립적인 Mdc 데이터를 유지한다.
      이를 통해 여러 쓰레드에서 처리된 로그가 섞이지 않고 특정 요청에 대해 별도의 컨텍스트 정보를 처리할 수 있다.
     */
    private void putMdc(TransactionType transactionType, TransactionStatus transactionStatus, OrderResult order) {
        Optional.ofNullable(order).ifPresentOrElse(this::putOrder, this::putNAOrder);
        putTransaction(transactionType, transactionStatus);
    }

    private void putOrder(OrderResult order) {
        MDC.put("orderId", order.getOrderId().toString());
        MDC.put("customerId", order.getCustomerId().toString());
        MDC.put("totalAmount", String.valueOf(order.getTotalAmount()));
        MDC.put("paymentMethod", order.getPaymentMethod().toString());
        MDC.put("productCount", order.getProductCount().toString());
        MDC.put("totalItemQuantity", order.getTotalItemQuantity().toString());
    }

    private void putNAOrder() {
        MDC.put("orderId", "N/A");
        MDC.put("customerId", "N/A");
        MDC.put("totalAmount", "N/A");
        MDC.put("paymentMethod", "N/A");
        MDC.put("productCount", "N/A");
        MDC.put("totalItemQuantity", "N/A");
    }

    private void putTransaction(TransactionType transactionType,
            TransactionStatus transactionStatus) {
        MDC.put("transactionType", transactionType.name());
        MDC.put("transactionStatus", transactionStatus.name());
    }

    private void log(TransactionStatus transactionStatus, String message) {
        if (transactionStatus == TransactionStatus.SUCCESS) {
            logger.info(message);
        } else {
            logger.error(message);
        }
    }

}
