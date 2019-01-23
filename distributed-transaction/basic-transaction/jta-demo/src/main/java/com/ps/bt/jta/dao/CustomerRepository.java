package com.ps.bt.jta.dao;

import com.ps.bt.jta.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findOneByUsername(String username);
}
