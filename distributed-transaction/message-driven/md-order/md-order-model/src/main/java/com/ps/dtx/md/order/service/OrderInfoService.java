package com.ps.dtx.md.order.service;

import com.ps.dtx.md.order.model.Order;

import java.util.List;

public interface OrderInfoService {
    Order getOrder(Integer Id);
    List<Order> getOrders(String userId);
}
