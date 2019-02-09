package com.ps.dtx.fd.order.dao.repository;

import com.ps.dtx.fd.order.dao.model.OrderTbl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void addOrder() {
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setCommodityCode("666");
        orderTbl.setCount(100);
        orderTbl.setMoney(1000);
        orderTbl.setUserId("1");
        Integer affectedRows = this.orderRepository.addOrder(orderTbl);
        Assert.assertTrue(affectedRows == 1);
    }
}