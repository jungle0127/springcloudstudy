package com.ps.ms.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.ms.order.dto.OrderDTO;
import com.ps.ms.user.domain.Customer;
import com.ps.ms.user.feign.OrderClient;
import com.ps.ms.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    @PostConstruct
    public void init() {
        Customer customer = new Customer();
        customer.setUsername("ps");
        customer.setPassword("111111");
        customer.setRole("User");
        customerRepository.save(customer);
    }

    @PostMapping("")
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    @GetMapping("")
    @HystrixCommand
    public List<Customer> getAll(){
        return this.customerRepository.findAll();
    }

    @GetMapping("/me")
    @HystrixCommand
    public Map getInfo(){
        Customer customer = this.customerRepository.findOneByUsername("ps");
        OrderDTO order = this.orderClient.getOrderProxy(1L);
        Map result = new HashMap();
        result.put("customer",customer);
        result.put("order", order);
        return result;
    }
}
