package com.ps.dtx.md.order.dao.repository;

import com.ps.dtx.md.order.dao.mapper.OrderTblMapper;
import com.ps.dtx.md.order.dao.model.OrderTbl;
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
