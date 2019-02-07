package com.ps.dtx.doubledb.ctxm.runner;

import com.ps.dtx.doubledb.ctxm.domain.Order;
import com.ps.dtx.doubledb.ctxm.service.OrderServiceInAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRunner implements CommandLineRunner {
    @Autowired
    private OrderServiceInAnnotation orderServiceInAnnotation;
    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setCustomerId(93511L);
        order.setAmount(10);
        order.setOrderDesc("pseudo order 1");
        order.setStorageId(1L);
        Boolean result = this.orderServiceInAnnotation.createOrder(order);
        System.out.println(result);
    }
}
