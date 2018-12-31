package com.ps.tx.md.order.controller;

import com.netflix.discovery.converters.Auto;
import com.ps.tx.md.order.IOrderService;
import com.ps.tx.md.order.domain.Order;
import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.order.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderServiceController implements IOrderService {
    @Auto
    private OrderRepository orderRepository;
    @PostConstruct
    public void initOneOrder(){
        Order order = new Order();
        order.setAmount(100);
        order.setDetail("detaisl");
        order.setId(1L);
        order.setTitle("title");
        orderRepository.save(order);
    }
    @PostMapping("")
    public Order createOrder(@RequestBody Order dto){
        return this.orderRepository.save(dto);
    }
    @GetMapping("")
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public OrderDTO getOrderProxy(@PathVariable Long id) {
        Order order = this.orderRepository.getOne(id);
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getDetail());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return dto;
    }
}
