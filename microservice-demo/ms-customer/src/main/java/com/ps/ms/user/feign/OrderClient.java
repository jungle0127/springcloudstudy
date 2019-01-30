package com.ps.ms.user.feign;

import com.ps.ms.order.dto.OrderDTO;
import com.ps.ms.order.service.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-order", path = "/api/v1/order")
public interface OrderClient extends IOrderService {
    @GetMapping("/{id}")
    OrderDTO getOrderProxy(@PathVariable(name = "id") Long id);
}
