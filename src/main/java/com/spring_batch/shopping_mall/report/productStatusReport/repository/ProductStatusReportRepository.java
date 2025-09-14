package com.spring_batch.shopping_mall.report.productStatusReport.repository;

import com.spring_batch.shopping_mall.report.productStatusReport.domain.ProductStatusReport;
import com.spring_batch.shopping_mall.report.productStatusReport.domain.ProductStatusReportId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusReportRepository extends
    JpaRepository<ProductStatusReport, ProductStatusReportId> {

  List<ProductStatusReport> findAllByStatDate(LocalDate statDate);

}
