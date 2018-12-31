package com.ps.tx.md.user.repository;

import com.ps.tx.md.user.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findOneByUsername(String username);
}
