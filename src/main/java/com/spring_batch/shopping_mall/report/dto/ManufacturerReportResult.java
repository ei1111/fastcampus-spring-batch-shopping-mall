package com.spring_batch.shopping_mall.report.dto;

import com.spring_batch.shopping_mall.report.manufacturerReport.domain.ManufacturerReport;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ManufacturerReportResult {

    private LocalDate statDate;
    private String manufacturer;
    private Long productCount;
    private Double avgSalesPrice;
    private Long totalStockQuantity;

    public static ManufacturerReportResult from(ManufacturerReport report) {
        return new ManufacturerReportResult(
                report.getStatDate(),
                report.getManufacturer(),
                report.getProductCount(),
                report.getAvgSalesPrice(),
                report.getTotalStockQuantity()
        );
    }
}