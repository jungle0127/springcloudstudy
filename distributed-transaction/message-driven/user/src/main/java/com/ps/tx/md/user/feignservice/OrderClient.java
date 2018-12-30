package com.ps.tx.md.user.feignservice;

import com.ps.tx.md.order.IOrderService;
import com.ps.tx.md.order.model.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order", path = "/api/v1/order")
public interface OrderClient extends IOrderService {
    @GetMapping("/{id}")
    OrderDTO getOrderProxy(@PathVariable(name = "id") Long id);
}
