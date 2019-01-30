package com.ps.tx.md.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.user.dao.model.MdCustomer;
import com.ps.tx.md.user.dao.repository.CustomerRepository;
import com.ps.tx.md.user.feignservice.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderClient orderClient;

    @PostMapping
    public void init() {
        MdCustomer customer = new MdCustomer();
        customer.setUsername("ps");
        customer.setPassword("111111");
        customer.setRole("User");
        this.customerRepository.save(customer);
    }

    @PostMapping("")
    public MdCustomer createCustomer(@RequestBody MdCustomer customer) {
        return this.customerRepository.save(customer);
    }

    @GetMapping("")
    @HystrixCommand
    public List<MdCustomer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @GetMapping("/me")
    @HystrixCommand
    public Map getMyInfo() {
        MdCustomer customer = this.customerRepository.findByUsername("ps");
        OrderDTO orderDTO = this.orderClient.getOrderProxy(1L);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("customer", customer);
        resultMap.put("order", orderDTO);
        return resultMap;
    }
}
