package com.ps.dtx.md.order.controller;

import com.ps.dtx.md.order.dao.repository.OrderRepository;
import com.ps.dtx.md.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id){
        return null;
    }
}
