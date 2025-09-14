package com.spring_batch.shopping_mall.report.controller;

import com.spring_batch.shopping_mall.report.dto.ProductReportResponse;
import com.spring_batch.shopping_mall.report.service.ProductReportService;
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

@RequestMapping("/v1/products/reports")
@RestController
@RequiredArgsConstructor
@Tag(name = "4. 보고서 API")
public class ProductReportController {

    private final ProductReportService productReportService;

    @GetMapping
    @Operation(summary = "일별 상품 현황 보고서")
    public ProductReportResponse getProductReports(
            @RequestParam("dt") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        return ProductReportResponse.from(productReportService.findReports(date));
    }

}