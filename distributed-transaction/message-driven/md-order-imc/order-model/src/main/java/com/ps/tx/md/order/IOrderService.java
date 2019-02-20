package com.ps.tx.md.order;

import com.ps.tx.md.order.model.OrderDTO;

public interface IOrderService {
    OrderDTO getOrderProxy(Long id);
}
