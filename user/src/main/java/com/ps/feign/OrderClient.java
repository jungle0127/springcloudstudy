package com.ps.feign;

import com.ps.dto.IOrderService;
import com.ps.dto.OrderDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order",path = "/api/order")
public interface OrderClient  extends IOrderService {
    @GetMapping("/{id}")
    OrderDTO getOrderProxy(@PathVariable(name = "id") Long id);
}
