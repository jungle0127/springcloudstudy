package com.ps.dtx.doubledb.ctxm.runner;

import com.ps.dtx.doubledb.ctxm.domain.Order;
import com.ps.dtx.doubledb.ctxm.service.OrderService;
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
        order.setAmount(10);
        order.setOrderDesc("pseudo order 1");
        order.setStorageId(1L);
        Boolean result = this.orderService.createOrder(order);
        System.out.println(result);
    }
}
