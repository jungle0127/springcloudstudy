package com.ps.dao;

import com.ps.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mavlarn on 2018/1/20.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOneByTitle(String title);
}
