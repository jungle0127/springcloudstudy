package com.ps.tx.jta.controller;

import com.ps.tx.jta.dao.CustomerRepository;
import com.ps.tx.jta.domain.Customer;
import com.ps.tx.jta.service.CustomerServiceInCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerServiceInCode customerServiceInCode;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/code")
    public Customer createCustomer(@RequestBody Customer customer) throws Exception {
        return this.customerServiceInCode.createCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

}
