package com.ps.dtx.md.facade.controller;

import com.ps.dtx.md.facade.feignservice.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/facade/order")
public class OrderController {
    @Autowired
    private OrderFeignClient orderFeignClient;
}
