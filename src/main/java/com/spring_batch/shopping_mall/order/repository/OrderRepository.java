package com.spring_batch.shopping_mall.order.repository;


import com.spring_batch.shopping_mall.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
