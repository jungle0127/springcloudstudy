package com.ps.web;

import com.ps.dao.OrderRepository;
import com.ps.domain.Order;
import com.ps.dto.IOrderService;
import com.ps.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource implements IOrderService {

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
        Order order = this.orderRepository.findOne(id);
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getDetail());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return dto;
    }

}
