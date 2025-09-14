package com.spring_batch.shopping_mall.report.dto;

import com.spring_batch.shopping_mall.report.categoryReport.domain.CategoryReport;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryReportResult {

    private LocalDate statDate;
    private String category;
    private Long productCount;
    private Double avgSalesPrice;
    private Integer maxSalesPrice;
    private Integer minSalesPrice;
    private Long totalStockQuantity;
    private Long potentialSalesAmount;

    public static CategoryReportResult from(CategoryReport categoryReport) {
        return new CategoryReportResult(
                categoryReport.getStatDate(),
                categoryReport.getCategory(),
                categoryReport.getProductCount(),
                categoryReport.getAvgSalesPrice(),
                categoryReport.getMaxSalesPrice(),
                categoryReport.getMinSalesPrice(),
                categoryReport.getTotalStockQuantity(),
                categoryReport.getPotentialSalesAmount()
        );
    }
}

