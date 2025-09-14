package com.spring_batch.shopping_mall.report.dto;

import com.spring_batch.shopping_mall.product.domain.ProductStatus;
import com.spring_batch.shopping_mall.report.productStatusReport.domain.ProductStatusReport;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStatusReportResult {

    private LocalDate statDate;
    private ProductStatus productStatus;
    private Long productCount;
    private Double avgStockQuantity;

    public static ProductStatusReportResult from(ProductStatusReport report) {
        return new ProductStatusReportResult(
                report.getStatDate(),
                report.getProductStatus(),
                report.getProductCount(),
                report.getAvgStockQuantity()
        );
    }
}
