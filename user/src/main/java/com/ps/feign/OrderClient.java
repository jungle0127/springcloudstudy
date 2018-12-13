package com.ps.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order",path = "/api/order")
public interface OrderClient {
    @GetMapping("/{id}")
    String getOrderProxy(@PathVariable(name = "id") Long id);
}
