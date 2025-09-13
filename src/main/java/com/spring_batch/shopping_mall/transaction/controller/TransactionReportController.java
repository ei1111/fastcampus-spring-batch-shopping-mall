package com.spring_batch.shopping_mall.transaction.controller;

import com.spring_batch.shopping_mall.transaction.dto.TransactionReportResponses;
import com.spring_batch.shopping_mall.transaction.service.TransactionReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Tag(name = "3. 일별 거래 데이터 조회 API")
public class TransactionReportController {

    private final TransactionReportService transactionReportService;

    @Operation(summary = "일별 거래 데이터 리포트")
    @GetMapping("/v1/transactions/reports")
    public TransactionReportResponses getTransactionReports(
            @RequestParam("dt") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        return TransactionReportResponses.from(transactionReportService.findByDate(date));
    }

}