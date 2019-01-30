package com.ps.ms.user.dao.repository;

import com.ps.ms.user.dao.mapper.CustomerMapper;
import com.ps.ms.user.dao.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer save(Customer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }

    public List<Customer> findAll() {
        return this.customerMapper.selectAll();
    }

    public Customer findOneByName(String userName) {
        return this.customerMapper.selectByUsername(userName);
    }
}
