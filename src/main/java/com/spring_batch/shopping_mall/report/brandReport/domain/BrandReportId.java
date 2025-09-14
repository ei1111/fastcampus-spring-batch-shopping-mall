package com.spring_batch.shopping_mall.report.brandReport.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandReportId implements Serializable {

  private LocalDate statDate;
  private String brand;

}
