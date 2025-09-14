package com.spring_batch.shopping_mall.report.manufacturerReport.repository;

import com.spring_batch.shopping_mall.report.manufacturerReport.domain.ManufacturerReport;
import com.spring_batch.shopping_mall.report.manufacturerReport.domain.ManufacturerReportId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerReportRepository extends
    JpaRepository<ManufacturerReport, ManufacturerReportId> {

  List<ManufacturerReport> findAllByStatDate(LocalDate statDate);

}
