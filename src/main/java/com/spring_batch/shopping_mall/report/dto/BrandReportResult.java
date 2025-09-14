package com.spring_batch.shopping_mall.report.dto;

import com.spring_batch.shopping_mall.report.brandReport.domain.BrandReport;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandReportResult {

    private LocalDate statDate;
    private String brand;
    private Long productCount;
    private Double avgSalesPrice;
    private Integer maxSalesPrice;
    private Integer minSalesPrice;
    private Long totalStockQuantity;
    private Double avgStockQuantity;
    private Long totalStockValue;

    public static BrandReportResult from(BrandReport brandReport) {
        return new BrandReportResult(
                brandReport.getStatDate(),
                brandReport.getBrand(),
                brandReport.getProductCount(),
                brandReport.getAvgSalesPrice(),
                brandReport.getMaxSalesPrice(),
                brandReport.getMinSalesPrice(),
                brandReport.getTotalStockQuantity(),
                brandReport.getAvgStockQuantity(),
                brandReport.getTotalStockValue()
        );
    }
}
