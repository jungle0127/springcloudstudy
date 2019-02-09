package com.ps.dtx.fd.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
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
    public void purchase(String userId, String commodityCode, int orderCount) {
        this.orderService.create(userId,commodityCode,orderCount);
        this.storageService.deduct(commodityCode,orderCount);
    }
}
