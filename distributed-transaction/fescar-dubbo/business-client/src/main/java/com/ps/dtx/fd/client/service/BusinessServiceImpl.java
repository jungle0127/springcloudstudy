package com.ps.dtx.fd.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.ps.dtx.fd.order.service.OrderService;
import com.ps.dtx.fd.storage.service.StorageService;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Reference
    private OrderService orderService;
    @Reference
    private StorageService storageService;

    @Override
    @GlobalTransactional(timeoutMills = 3000,name = "fescar-nacos-gts-demo")
    public void purchase(String userId, String commodityCode, int orderCount) {
        this.orderService.create(userId,commodityCode,orderCount);
        this.storageService.deduct(commodityCode,orderCount);
    }
}
