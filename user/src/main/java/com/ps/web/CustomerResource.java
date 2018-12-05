package com.ps.web;

import com.ps.dao.CustomerRepository;
import com.ps.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Customer> getAll(){
        return this.customerRepository.findAll();
    }
}
