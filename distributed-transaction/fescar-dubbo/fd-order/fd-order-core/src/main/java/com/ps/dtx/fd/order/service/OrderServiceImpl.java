package com.ps.dtx.fd.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ps.dtx.fd.account.service.AccountService;
import com.ps.dtx.fd.order.dao.model.OrderTbl;
import com.ps.dtx.fd.order.dao.repository.OrderRepository;
import com.ps.dtx.fd.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Reference(url = "zookeeper://192.168.1.6:2181",interfaceClass = AccountService.class)
    private AccountService accountService;
    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        Integer orderMoney = this.calculate(commodityCode,1);
        this.accountService.debit(userId,orderMoney);
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setCommodityCode(commodityCode);
        orderTbl.setUserId(userId);
        orderTbl.setMoney(orderMoney);
        orderTbl.setCount(orderCount);
        this.orderRepository.addOrder(orderTbl);
        Order order = new Order();
        order.setCommodityCode(orderTbl.getCommodityCode());
        order.setCount(orderTbl.getCount());
        order.setMoney(orderTbl.getMoney());
        order.setUserId(orderTbl.getUserId());
        return order;
    }
    private int calculate(String commodityId, int orderCount) {
        return 100 * orderCount;
    }
}
