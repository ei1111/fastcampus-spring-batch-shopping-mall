package com.spring_batch.shopping_mall.report.productStatusReport.domain;

import com.spring_batch.shopping_mall.product.domain.ProductStatus;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusReportId implements Serializable {

    private LocalDate statDate;
    private ProductStatus productStatus;

}
