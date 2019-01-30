package com.ps.ms.order.dao.repository;

import com.ps.ms.order.dao.mapper.OrderMapper;
import com.ps.ms.order.dao.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private OrderMapper orderMapper;

    public Order save(Order order) {
        this.orderMapper.insert(order);
        return order;
    }

    public List<Order> findAll() {
        return this.orderMapper.selectAll();
    }

    public Order findById(Long id) {
        return this.orderMapper.selectByPrimaryKey(id);
    }
}
