package com.ps.web;

import com.ps.dao.OrderRepository;
import com.ps.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource  {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("")
    public Order create(@RequestBody Order dto) {
        return orderRepository.save(dto);
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
