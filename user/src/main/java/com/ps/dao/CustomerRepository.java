package com.ps.dao;

import com.ps.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ps
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}
