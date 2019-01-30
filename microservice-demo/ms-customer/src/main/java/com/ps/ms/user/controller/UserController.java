package com.ps.ms.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.ms.order.dto.OrderDTO;
import com.ps.ms.user.dao.model.Customer;
import com.ps.ms.user.dao.repository.UserRepository;
import com.ps.ms.user.feign.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("")
    public Customer create(@RequestBody Customer customer) {
        return userRepository.save(customer);
    }

    @GetMapping("")
    @HystrixCommand
    public List<Customer> getAll() {
        return this.userRepository.findAll();
    }

    @GetMapping("/me")
    @HystrixCommand
    public Map getInfo() {
        Customer customer = this.userRepository.findOneByName("ps");
        OrderDTO order = this.orderClient.getOrderProxy(1L);
        Map result = new HashMap();
        result.put("customer", customer);
        result.put("order", order);
        return result;
    }
}
