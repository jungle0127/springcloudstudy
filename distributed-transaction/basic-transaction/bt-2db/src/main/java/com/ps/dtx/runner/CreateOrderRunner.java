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
        order.setCommodityCode("cc1");
        order.setCount(100);
        order.setMoney(10);
        order.setUserId("uid1");
        Boolean result = this.orderService.createOrder(order);
        System.out.println(result);
    }
}
