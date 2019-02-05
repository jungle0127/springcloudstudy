package com.ps.dtx.besteffort.dao;

import com.ps.dtx.besteffort.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
