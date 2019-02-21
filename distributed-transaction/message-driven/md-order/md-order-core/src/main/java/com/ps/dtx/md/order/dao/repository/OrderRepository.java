package com.ps.dtx.md.order.dao.repository;

import com.ps.dtx.md.order.dao.mapper.OrderTblMapper;
import com.ps.dtx.md.order.dao.model.OrderTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private OrderTblMapper orderTblMapper;

    public Integer addOrder(OrderTbl orderTbl){
        return this.orderTblMapper.insert(orderTbl);
    }

    public OrderTbl getOrder(Integer id){
        return this.orderTblMapper.selectByPrimaryKey(id);
    }
    public List<OrderTbl> getOrders(String userId){
        return this.orderTblMapper.selectByUserId(userId);
    }
}
