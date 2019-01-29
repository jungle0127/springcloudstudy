package com.ps.dtx.web;

import com.ps.dtx.domain.Order;
import com.ps.dtx.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/order")
    public void create(@RequestBody Order order){
        this.customerService.createOrder(order);
    }
    @GetMapping("/{id}")
    public Map userInfo(@PathVariable Long id){
        return this.customerService.userInfo(id);
    }
}
