package com.javastudy.web;

import com.javastudy.dao.CustomerRepository;
import com.javastudy.domain.Customer;
import com.javastudy.service.CustomerServiceTxInAnnotation;
import com.javastudy.service.CustomerServiceTxInCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerServiceTxInAnnotation customerServiceTxInAnnotation;
    @Autowired
    private CustomerServiceTxInCode customerServiceTxInCode;
    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer){
        return this.customerServiceTxInAnnotation.create(customer);
    }
    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer){
        return this.customerServiceTxInCode.create(customer);
    }
    @GetMapping("/customers")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
