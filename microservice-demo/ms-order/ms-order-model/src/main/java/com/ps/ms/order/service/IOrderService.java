package com.ps.ms.order.service;

import com.ps.ms.order.dto.OrderDTO;

public interface IOrderService {
    OrderDTO getOrderProxy(Long id);
}
