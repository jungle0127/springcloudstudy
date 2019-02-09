package com.ps.dtx.fd.order.dao.repository;

import com.ps.dtx.fd.order.dao.mapper.OrderTblMapper;
import com.ps.dtx.fd.order.dao.model.OrderTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    @Autowired
    private OrderTblMapper orderTblMapper;

    public Integer addOrder(OrderTbl orderTbl){
        return this.orderTblMapper.insert(orderTbl);
    }
}
