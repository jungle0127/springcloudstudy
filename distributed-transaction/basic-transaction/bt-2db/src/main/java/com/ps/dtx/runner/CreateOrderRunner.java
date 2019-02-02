package com.ps.dtx.runner;

import com.ps.dtx.domain.Order;
import com.ps.dtx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRunner implements CommandLineRunner {
    @Autowired
    private OrderService orderService;
    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setCustomerId(93511L);
        order.setAmmount(10);
        order.setOrderDesc("pseudo order 1");
        order.setStorageId(1L);
        Boolean result = this.orderService.createOrder(order);
        System.out.println(result);
    }
}
