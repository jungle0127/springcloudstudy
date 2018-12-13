package com.ps.web;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.dao.CustomerRepository;
import com.ps.domain.Customer;
import com.ps.feign.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

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

    @GetMapping("/my")
    @HystrixCommand
    public Map getInfo(){
        Customer customer = this.customerRepository.findOneByUsername("ps");
        String order = this.orderClient.getOrderProxy(1L);
        Map result = new HashMap();
        result.put("customer",customer);
        result.put("order", order);
        return result;
    }
}
