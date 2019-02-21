package com.ps.dtx.md.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.dtx.md.order.dao.model.OrderTbl;
import com.ps.dtx.md.order.dao.repository.OrderRepository;
import com.ps.dtx.md.order.model.Order;
import com.ps.dtx.md.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController implements OrderInfoService {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/{id}")
    @HystrixCommand
    public Order getOrder(@PathVariable Integer id){
        OrderTbl pojo = this.orderRepository.getOrder(id);
        return transform(pojo);
    }
    @GetMapping("/userId/{userId}")
    @HystrixCommand
    public List<Order> getOrders(@PathVariable String userId){
        List<OrderTbl> orderList = this.orderRepository.getOrders(userId);
        List<Order> pojoList = new LinkedList<>();
        for(OrderTbl pojo: orderList){
            pojoList.add(this.transform(pojo));
        }
        return pojoList;
    }
    private Order transform(OrderTbl pojo){
        if(pojo == null){
            return new Order();
        }
        Order order = new Order();
        order.setCommodityCode(pojo.getCommodityCode());
        order.setCount(pojo.getCount());
        order.setId(pojo.getId());
        order.setMoney(pojo.getMoney());
        order.setUserId(pojo.getUserId());
        return order;
    }
}
