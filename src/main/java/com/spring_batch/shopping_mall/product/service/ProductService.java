package com.spring_batch.shopping_mall.product.service;

import static com.spring_batch.shopping_mall.global.error.ErrorCode.NOT_FOUND_PRODUCT;

import com.spring_batch.shopping_mall.global.error.CustomException;
import com.spring_batch.shopping_mall.product.domain.Product;
import com.spring_batch.shopping_mall.product.dto.ProductResult;
import com.spring_batch.shopping_mall.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResult findProduct(String productId) {
        return ProductResult.from(findProductById(productId));
    }

    private Product findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_PRODUCT));
    }

    public Page<ProductResult> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.map(ProductResult::from);
    }

    @Transactional
    public void decreaseStock(String productId, int quantity) {
        Product product = findProductById(productId);
        product.decreaseStock(quantity);
        productRepository.save(product);
    }

    @Transactional
    public void increaseStock(String productId, int quantity) {
        Product product = findProductById(productId);
        product.increaseStock(quantity);
        productRepository.save(product);
    }
}

