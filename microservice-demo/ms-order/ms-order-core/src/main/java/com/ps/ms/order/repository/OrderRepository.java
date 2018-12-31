package com.ps.ms.order.repository;

import com.ps.ms.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOneByTitle(String title);
}
