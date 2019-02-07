package com.ps.dtx.jta.runner;

import com.ps.dtx.jta.config.XAConfiguration;
import com.ps.dtx.jta.domain.Order;
import com.ps.dtx.jta.service.OrderServiceAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRunner implements CommandLineRunner {
    @Autowired
    private OrderServiceAnnotation orderService;
    @Autowired
    private XAConfiguration xaConfiguration;
    @Override
    public void run(String... args) throws Exception {
        this.createOrder();
    }

    private void testXAConfig(){
        System.out.println(this.xaConfiguration.toString());
    }

    private void createOrder(){
        Order order = new Order();
        order.setCustomerId(93511L);
        order.setAmmount(10);
        order.setOrderDesc("pseudo order 1");
        order.setStorageId(1L);
        Boolean result = this.orderService.createOrder(order);
        System.out.println(result);
    }
}
