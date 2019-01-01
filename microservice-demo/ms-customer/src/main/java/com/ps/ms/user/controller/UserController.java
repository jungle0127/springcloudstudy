package com.ps.ms.user.controller;

import com.ps.ms.user.dao.model.Customer;
import com.ps.ms.user.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public Customer create(@RequestBody Customer customer) {
        return userRepository.save(customer);
    }
    @GetMapping("")
//    @HystrixCommand
    public List<Customer> getAll(){
        return this.userRepository.findAll();
    }
}
