package com.ps.tx.md.user.dao.repository;

import com.ps.tx.md.user.dao.mapper.MdCustomerMapper;
import com.ps.tx.md.user.dao.model.MdCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    private MdCustomerMapper customerMapper;

    public MdCustomer save(MdCustomer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }

    public List<MdCustomer> findAll() {
        return this.customerMapper.selectAll();
    }

    public MdCustomer findByUsername(String userName) {
        return this.customerMapper.selectByUsername(userName);
    }
}
