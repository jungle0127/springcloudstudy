package com.ps.infrastructure.database.multiple.dynamic.datasource.runner;

import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.model.TOrder;
import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public void run(String... args) throws Exception {
        TOrder order = new TOrder();
        order.setAmount(100);
        order.setCreateTime(new Date());
        order.setCustomerId(1L);
        order.setOrderDesc("multiple datasource");
        order.setStorageId(2L);
        Integer affectedRows = this.orderRepository.addOrder(order);
        System.out.println("affected rows for adding order:" + affectedRows);
        List<TOrder> orderList = this.orderRepository.getAllOrders();
        for(TOrder pojo: orderList){
            System.out.println(pojo.toString());
        }
    }
}
