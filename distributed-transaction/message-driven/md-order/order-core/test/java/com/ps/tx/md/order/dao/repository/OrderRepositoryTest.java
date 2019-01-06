package com.ps.tx.md.order.dao.repository;

import com.ps.tx.md.order.dao.model.MdOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void findByCustomerId() {
        List<MdOrder> orderList = this.orderRepository.findByCustomerId(123L);
        Assert.assertTrue(orderList.size() == 2);
        for(MdOrder order: orderList){
            System.out.println(order.getUuid());
        }
    }

    @Test
    public void findOneByUuid() {
        MdOrder order = this.orderRepository.findOneByUuid("uuid");
        Assert.assertTrue(order.getCustomerId() == 123L);
        System.out.println(order.getTicketNumber());
    }
}