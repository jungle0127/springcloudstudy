package com.ps.dtx.fd.order.service;

import com.ps.dtx.fd.order.model.Order;

public interface OrderService {
    /**
     * create order
     */
    Order create(String userId, String commodityCode, int orderCount);
}
