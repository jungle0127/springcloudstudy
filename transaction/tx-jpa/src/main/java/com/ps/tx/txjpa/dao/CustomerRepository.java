package com.ps.tx.txjpa.dao;

import com.ps.tx.txjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByUsername(String username);

}
