package com.ps.dtx.md.facade.feignservice;

import com.ps.dtx.md.order.model.Order;
import com.ps.dtx.md.order.service.OrderInfoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "md-order",path = "/api/v1/order")
public interface OrderFeignClient extends OrderInfoService {
    @GetMapping("/{id}")
    Order getOrder(@PathVariable(name = "id") Integer id);
    @GetMapping("/userId/{userId}")
    List<Order> getOrders(@PathVariable(name = "userId") String userId);
}
