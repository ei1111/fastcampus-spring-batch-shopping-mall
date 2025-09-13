package com.spring_batch.shopping_mall.transaction.repository;

import com.spring_batch.shopping_mall.transaction.domain.TransactionReport;
import com.spring_batch.shopping_mall.transaction.domain.TransactionReportId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, TransactionReportId> {

    List<TransactionReport> findAllByTransactionDate(LocalDate transactionDate);
}