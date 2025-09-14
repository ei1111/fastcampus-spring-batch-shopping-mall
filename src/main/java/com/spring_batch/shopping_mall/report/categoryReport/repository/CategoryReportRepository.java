package com.spring_batch.shopping_mall.report.categoryReport.repository;

import com.spring_batch.shopping_mall.report.categoryReport.domain.CategoryReport;
import com.spring_batch.shopping_mall.report.categoryReport.domain.CategoryReportId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReportRepository extends JpaRepository<CategoryReport, CategoryReportId> {

  List<CategoryReport> findAllByStatDate(LocalDate statDate);

}
