package com.ps.ms.order.controller;

import com.ps.ms.order.dao.model.Order;
import com.ps.ms.order.dao.repository.OrderRepository;
import com.ps.ms.order.dto.OrderDTO;
import com.ps.ms.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController implements IOrderService {
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

    @GetMapping("/{id}")
    public OrderDTO getOrderProxy(@PathVariable Long id) {
        Order order = this.orderRepository.findById(id);
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getDetail());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return dto;
    }
}
