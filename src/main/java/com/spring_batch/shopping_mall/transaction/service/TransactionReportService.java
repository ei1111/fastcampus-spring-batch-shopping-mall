package com.spring_batch.shopping_mall.transaction.service;

import com.spring_batch.shopping_mall.transaction.dto.TransactionReportResults;
import com.spring_batch.shopping_mall.transaction.repository.TransactionReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionReportService {

    private final TransactionReportRepository repository;

    public TransactionReportResults findByDate(LocalDate date) {
        return TransactionReportResults.from(repository.findAllByTransactionDate(date));
    }
}