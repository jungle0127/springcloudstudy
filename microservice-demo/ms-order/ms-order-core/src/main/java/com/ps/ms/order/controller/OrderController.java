package com.ps.ms.order.controller;

import com.ps.ms.order.domain.Order;
import com.ps.ms.order.dto.OrderDTO;
import com.ps.ms.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
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
    public OrderDTO getOrderProxy(@PathVariable Long id){
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        Order order = optionalOrder.get();
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getDetail());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return dto;
    }
}
