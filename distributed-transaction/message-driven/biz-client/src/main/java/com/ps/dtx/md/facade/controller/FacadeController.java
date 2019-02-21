package com.ps.dtx.md.facade.controller;

import com.ps.dtx.md.facade.feignservice.AccountFeignClient;
import com.ps.dtx.md.facade.feignservice.OrderFeignClient;
import com.ps.dtx.md.facade.feignservice.StorageFeignClient;
import com.ps.dtx.md.facade.model.OrderDTO;
import com.ps.dtx.md.order.model.Order;
import com.ps.dtx.md.order.model.OrderJmsDestinationConstant;
import com.ps.dtx.md.storage.model.Storage;
import com.ps.dtx.md.storage.model.StorageJmsDestinationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/facade")
public class FacadeController {
    private AccountFeignClient accountFeignClient;
    private OrderFeignClient orderFeignClient;
    private StorageFeignClient storageFeignClient;
    @Autowired
    private JmsTemplate jmsTemplate;
    @PostMapping("/purchase")
    @Transactional
    public void purchase(@RequestBody OrderDTO dto){
        Storage storage = new Storage();
        storage.setCommodityCode(dto.getCommodityCode());
        storage.setCount(dto.getOrderCount());
        this.jmsTemplate.convertAndSend(StorageJmsDestinationConstant.STORAGE_CREATE_DESTINATION,storage);
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setCommodityCode(dto.getCommodityCode());
        order.setCount(dto.getOrderCount());
        order.setMoney(10);
        this.jmsTemplate.convertAndSend(OrderJmsDestinationConstant.ORDER_CREATE_DESTINATION, order);
    }


    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setAccountFeignClient(AccountFeignClient accountFeignClient) {
        this.accountFeignClient = accountFeignClient;
    }

    public void setOrderFeignClient(OrderFeignClient orderFeignClient) {
        this.orderFeignClient = orderFeignClient;
    }

    public void setStorageFeignClient(StorageFeignClient storageFeignClient) {
        this.storageFeignClient = storageFeignClient;
    }
}
