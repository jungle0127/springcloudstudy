package com.ps.dtx.fd.order.service;

import com.ps.dtx.fd.order.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootApplication
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void create() {
        Order order = this.orderService.create("1","123",2);
        Assert.assertNotNull(order);
    }
}