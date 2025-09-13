package com.spring_batch.shopping_mall.transaction.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReportId implements Serializable {

    private LocalDate transactionDate;
    private String transactionType;
}

