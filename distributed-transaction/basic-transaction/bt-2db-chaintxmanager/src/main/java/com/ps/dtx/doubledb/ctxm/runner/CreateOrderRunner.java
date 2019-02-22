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
        order.setCommodityCode("cc1");
        order.setCount(100);
        order.setMoney(10);
        order.setUserId("uid1");
        Boolean result = this.orderServiceInAnnotation.createOrder(order);
        System.out.println(result);
    }
}
