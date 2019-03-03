package com.ps.dtx.fd.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.ps.dtx.fd.order.service.OrderService;
import com.ps.dtx.fd.storage.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Reference
    private OrderService orderService;
    @Reference
    private StorageService storageService;
    private boolean flag = false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    @GlobalTransactional(timeoutMills = 3000,name = "fescar-nacos-gts-demo")
    public void purchase(String userId, String commodityCode, int orderCount) {
        logger.info("Begin global tranaction with XID: {}", RootContext.getXID());
        this.orderService.create(userId,commodityCode,orderCount);
        this.storageService.deduct(commodityCode,orderCount);
        if(this.flag){
            throw new RuntimeException("pseudo exception for rolling back global transaction.");
        }
    }
}
