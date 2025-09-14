package com.spring_batch.shopping_mall.report.brandReport.repository;

import com.spring_batch.shopping_mall.report.brandReport.domain.BrandReport;
import com.spring_batch.shopping_mall.report.brandReport.domain.BrandReportId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandReportRepository extends JpaRepository<BrandReport, BrandReportId> {

  List<BrandReport> findAllByStatDate(LocalDate statDate);
}
