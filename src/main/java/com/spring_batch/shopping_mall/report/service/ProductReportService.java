package com.spring_batch.shopping_mall.report.service;

import com.spring_batch.shopping_mall.report.brandReport.repository.BrandReportRepository;
import com.spring_batch.shopping_mall.report.categoryReport.repository.CategoryReportRepository;
import com.spring_batch.shopping_mall.report.dto.ProductReportResults;
import com.spring_batch.shopping_mall.report.manufacturerReport.repository.ManufacturerReportRepository;
import com.spring_batch.shopping_mall.report.productStatusReport.repository.ProductStatusReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReportService {

    private final BrandReportRepository brandReportRepository;
    private final CategoryReportRepository categoryReportRepository;
    private final ManufacturerReportRepository manufacturerReportRepository;
    private final ProductStatusReportRepository productStatusReportRepository;

    public ProductReportResults findReports(LocalDate date) {
        return ProductReportResults.of(brandReportRepository.findAllByStatDate(date),
                categoryReportRepository.findAllByStatDate(date),
                manufacturerReportRepository.findAllByStatDate(date),
                productStatusReportRepository.findAllByStatDate(date)
        );
    }
}

