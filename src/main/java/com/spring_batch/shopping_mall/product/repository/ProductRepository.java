package com.spring_batch.shopping_mall.product.repository;

import com.spring_batch.shopping_mall.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}

