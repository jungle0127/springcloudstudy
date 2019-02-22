package com.ps.dtx.md.order.service.impl;

import com.ps.dtx.md.account.model.Account;
import com.ps.dtx.md.account.model.AccountJmsDestinationConstant;
import com.ps.dtx.md.order.dao.model.OrderTbl;
import com.ps.dtx.md.order.dao.repository.OrderRepository;
import com.ps.dtx.md.order.model.Order;
import com.ps.dtx.md.order.model.OrderJmsDestinationConstant;
import com.ps.dtx.md.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    @JmsListener(destination = OrderJmsDestinationConstant.ORDER_CREATE_DESTINATION,containerFactory = "jmsListenerContainerFactory")
    @Transactional
    public void create(Order order) {
        Integer affectedRows = this.orderRepository.addOrder(transform(order));
        logger.info("Created order with affected rows:{}", affectedRows);
        Account account = new Account();
        account.setUserId(order.getUserId());
        account.setMoney(order.getMoney() * order.getCount());
        this.jmsTemplate.convertAndSend(AccountJmsDestinationConstant.ACCOUNT_DEBIT_DESTINATION,account);
    }
    private OrderTbl transform(Order order){
        if(null == order){
            return new OrderTbl();
        }
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setCommodityCode(order.getCommodityCode());
        orderTbl.setCount(order.getCount());
        orderTbl.setMoney(order.getMoney());
        orderTbl.setUserId(order.getUserId());
        return orderTbl;
    }
}
