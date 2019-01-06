package com.ps.tx.md.order.controller;

import com.netflix.discovery.converters.Auto;
import com.ps.tx.md.order.IOrderService;
import com.ps.tx.md.order.dao.model.MdOrder;
import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.order.dao.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderServiceController implements IOrderService {
    @Auto
    private OrderRepository orderRepository;
    @PostMapping("")
    public MdOrder createOrder(@RequestBody MdOrder dto){
        return this.orderRepository.addOrder(dto);
    }
    @GetMapping("")
    public List<MdOrder> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public OrderDTO getOrderProxy(@PathVariable Long id) {
        MdOrder order = this.orderRepository.selectById(id);
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getStatus());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return null;
    }
}
