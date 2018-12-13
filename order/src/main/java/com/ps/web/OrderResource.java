package com.ps.web;

import com.ps.dao.OrderRepository;
import com.ps.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource  {

    @Autowired
    private OrderRepository orderRepository;
    @PostConstruct
    public void init(){
        Order order = new Order();
        order.setAmount(100);
        order.setDetail("detaisl");
        order.setId(1L);
        order.setTitle("title");
        orderRepository.save(order);
    }
    @PostMapping("")
    public Order create(@RequestBody Order dto) {
        return orderRepository.save(dto);
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id){
        Order order = this.orderRepository.findOne(id);
        return order.getTitle();
    }

}
