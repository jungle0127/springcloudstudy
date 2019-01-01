package com.ps.tx.md.order.repository;


import com.ps.tx.md.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOneByTitle(String title);
}