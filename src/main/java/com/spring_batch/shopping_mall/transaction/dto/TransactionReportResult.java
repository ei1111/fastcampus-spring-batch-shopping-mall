package com.spring_batch.shopping_mall.transaction.dto;

import com.spring_batch.shopping_mall.transaction.domain.TransactionReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionReportResult {

    private LocalDate transactionDate;
    private String transactionType;
    private Long transactionCount;
    private Long totalAmount;
    private Long customerCount;
    private Long orderCount;
    private Long paymentMethodCount;
    private BigDecimal avgProductCount;
    private Long totalItemQuantity;

    public static TransactionReportResult from(TransactionReport transactionReport) {
        return new TransactionReportResult(
                transactionReport.getTransactionDate(),
                transactionReport.getTransactionType(),
                transactionReport.getTransactionCount(),
                transactionReport.getTotalAmount(),
                transactionReport.getCustomerCount(),
                transactionReport.getOrderCount(),
                transactionReport.getPaymentMethodCount(),
                transactionReport.getAvgProductCount(),
                transactionReport.getTotalItemQuantity()
        );
    }
}